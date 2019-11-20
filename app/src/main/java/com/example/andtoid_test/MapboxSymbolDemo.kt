package com.example.android_test

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast

import com.mapbox.mapboxsdk.Mapbox
import com.mapbox.mapboxsdk.geometry.LatLng
import com.mapbox.mapboxsdk.maps.MapView
import com.mapbox.mapboxsdk.maps.MapboxMap
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback
import com.mapbox.mapboxsdk.maps.Style
import com.mapbox.mapboxsdk.plugins.annotation.OnSymbolDragListener
import com.mapbox.mapboxsdk.plugins.annotation.OnSymbolLongClickListener
import com.mapbox.mapboxsdk.plugins.annotation.Symbol
import com.mapbox.mapboxsdk.plugins.annotation.SymbolManager
import com.mapbox.mapboxsdk.plugins.annotation.SymbolOptions

/**
 * Change symbol icon by pressing on icon
 */
class MapboxSymbolDemo : AppCompatActivity(), OnMapReadyCallback {

    private var mapView: MapView? = null
    private var symbolManager: SymbolManager? = null
    private var symbol: Symbol? = null
    private var symbol2: Symbol? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Mapbox access token is configured here. This needs to be called either in your application
        // object or in the same activity which contains the mapview.
        // 勇哥 的 token ，省着点用
        Mapbox.getInstance(this, "pk.eyJ1IjoiaGdkc2dldmVuIiwiYSI6ImNqeDVoMWVpajAxeDU0OXF0Nmk3NnQ2cTEifQ.9osf4ArIUjRVvB1eNvrZ5g")

        // This contains the MapView in XML and needs to be called after the access token is configured.
        setContentView(R.layout.mapbox_symbol_demo)

        mapView = findViewById(R.id.map_view)
        mapView!!.onCreate(savedInstanceState)
        mapView!!.getMapAsync(this)
    }

    override fun onMapReady(mapboxMap: MapboxMap) {
        mapboxMap.setStyle(Style.DARK) { style ->
            // Set up a SymbolManager instance
            symbolManager = SymbolManager(mapView!!, mapboxMap, style)

            // Add symbol at specified lat/lon
            symbol = symbolManager!!.create(
                SymbolOptions()
                    .withLatLng(LatLng(60.169091, 24.939876))
                    .withIconImage(MAKI_ICON_CAFE)
                    .withIconSize(2.0f)
                    .withDraggable(true)
                    .withTextField("hello world")
                    .withSymbolSortKey(0.5.toFloat())
            )

            symbol2 = symbolManager!!.create(
                SymbolOptions()
                    .withLatLng(LatLng(60.16095, 24.939879)) // 稍微偏移一点
                    .withIconImage(MAKI_ICON_HARBOR)
                    .withIconSize(2.0f)
                    .withDraggable(true)
                    .withTextField("awesome")
                    .withSymbolSortKey(2.0.toFloat())
            )

            // 坑爹玩意，不能设置文本不重合，图片重合
            symbolManager!!.textIgnorePlacement = false
            symbolManager!!.textAllowOverlap = false
            symbolManager!!.iconIgnorePlacement = true
            symbolManager!!.iconAllowOverlap = true

            // Add click listener and change the symbol to a cafe icon on click
            symbolManager!!.addClickListener { symbol ->
                Toast.makeText(
                    this@MapboxSymbolDemo,
                    "symbol clicked", Toast.LENGTH_SHORT
                ).show()
                symbol.iconImage = MAKI_ICON_CAFE
                symbolManager!!.update(symbol)
            }

            // Add long click listener and change the symbol to an airport icon on long click
            symbolManager!!.addLongClickListener(OnSymbolLongClickListener { symbol ->
                Toast.makeText(
                    this@MapboxSymbolDemo,
                    "symbol long clicked", Toast.LENGTH_SHORT
                ).show()
                symbol.iconImage = MAKI_ICON_AIRPORT
                symbolManager!!.update(symbol)
            })

            symbolManager!!.addDragListener(object : OnSymbolDragListener {
                override// Left empty on purpose
                fun onAnnotationDragStarted(annotation: Symbol) {
                }

                override// Left empty on purpose
                fun onAnnotationDrag(symbol: Symbol) {
                }

                override// Left empty on purpose
                fun onAnnotationDragFinished(annotation: Symbol) {
                }
            })
            Toast.makeText(
                this@MapboxSymbolDemo,
                "symbol_listener_instruction_toast",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    public override fun onResume() {
        super.onResume()
        mapView!!.onResume()
    }

    override fun onStart() {
        super.onStart()
        mapView!!.onStart()
    }

    override fun onStop() {
        super.onStop()
        mapView!!.onStop()
    }

    public override fun onPause() {
        super.onPause()
        mapView!!.onPause()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView!!.onLowMemory()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView!!.onDestroy()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mapView!!.onSaveInstanceState(outState)
    }

    companion object {
        private val MAKI_ICON_CAFE = "cafe-15"
        private val MAKI_ICON_HARBOR = "harbor-15"
        private val MAKI_ICON_AIRPORT = "airport-15"
    }
}