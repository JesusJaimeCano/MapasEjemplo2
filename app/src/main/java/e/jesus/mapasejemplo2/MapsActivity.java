package e.jesus.mapasejemplo2;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.Arrays;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    boolean mapaListo = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-19, 99);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

        boolean success = googleMap.setMapStyle(
                MapStyleOptions.loadRawResourceStyle(MapsActivity.this, R.raw.style_json)
        );


        mapaListo = true;
    }

    public void ponerMarcador(View v) {
        if (mapaListo) {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) getResources().getDrawable(R.drawable.marcador_na_at);

            Bitmap b = bitmapDrawable.getBitmap();
            Bitmap smallMarker = Bitmap.createScaledBitmap(b, 150, 150, false);
            LatLng ubicacion = new LatLng(19.40961826, -99.16942731);
            Marker marcador = mMap.addMarker(new MarkerOptions().position(ubicacion));

            marcador.setTitle("Na-at Technologies");
            marcador.setIcon(BitmapDescriptorFactory.fromBitmap(smallMarker));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ubicacion, 17f));
        }
    }

    public void ponerLinea(View v) {
        if (mapaListo) {
            PolylineOptions options = new PolylineOptions()
                    .add(new LatLng(19.422447, -99.1692277))
                    .add(new LatLng(19.419821, -99.166503))
                    .add(new LatLng(19.415849, -99.170129))
                    .add(new LatLng(19.413279, -99.167940))
                    .width(32)
                    .color(Color.MAGENTA);
            Polyline polyline = mMap.addPolyline(options);


            List<LatLng> puntos = polyline.getPoints();
            puntos.add(new LatLng(19.412753, -99.166170));


            polyline.setPoints(puntos);

            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(19.422447, -99.1692277), 14.0f));


        }
    }

    public void ponerPoligono(View v) {
        if (mapaListo) {
            PolygonOptions options = new PolygonOptions()
                    .add(
                            new LatLng(19.415146, -99.169550),
                            new LatLng(19.410527, -99.171846),
                            new LatLng(19.409505, -99.171385),
                            new LatLng(19.409743, -99.168944),
                            new LatLng(19.415281, -99.166476)
                    ).fillColor(Color.MAGENTA)
                    .strokeColor(Color.BLUE)
                    .addHole(Arrays.asList(
                            new LatLng(19.414279, -99.168987),
                            new LatLng(19.410606, -99.170950),
                            new LatLng(19.410293, -99.169178),
                            new LatLng(19.413551, -99.167773)
                    ));

            mMap.addPolygon(options);
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(19.412215, -99.169157), 16.5f));
        }
    }

    public void ponerCirculo(View v) {
        if (mapaListo) {
            CircleOptions circleOptions = new CircleOptions()
                    .center(new LatLng(19.421238, -99.185304))
                    .radius(500)
                    .fillColor(Color.MAGENTA);

            mMap.addCircle(circleOptions);
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(19.421238, -99.185304), 14.0f));
        }
    }

    boolean estiloMapa = true;

    public void ponerEstilo(View v) {
        if (estiloMapa) {
            mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
            estiloMapa = false;
        } else {
            mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
            estiloMapa = false;
        }
    }

    public void limpiarMapa(View v) {
        if (mapaListo) {
            mMap.clear();
        }
    }
}
