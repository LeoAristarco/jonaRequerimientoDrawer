package com.example.jonatan.jonanavigationdrawer;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.jonatan.jonanavigationdrawer.vistas.ActivityDescripcionNoticia;
import com.example.jonatan.jonanavigationdrawer.vistas.ContenedorFragment;
import com.example.jonatan.jonanavigationdrawer.vistas.FragmentDetalleNumero;
import com.example.jonatan.jonanavigationdrawer.vistas.FragmentList;
import com.example.jonatan.jonanavigationdrawer.vistas.GreenFragment;
import com.example.jonatan.jonanavigationdrawer.vistas.RedFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, RedFragment.OnFragmentInteractionListener,GreenFragment.OnFragmentInteractionListener,
        ContenedorFragment.OnFragmentInteractionListener, FragmentDetalleNumero.OnFragmentInteractionListener,
        FragmentList.OnFragmentInteractionListener, IComunicaFragments {

    FragmentDetalleNumero fragmentDetalleNumero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        fragmentDetalleNumero= new FragmentDetalleNumero();

        cambiarDeFragment(new ContenedorFragment());
        navigationView.getMenu().getItem(0).setChecked(true);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            cambiarDeFragment(new ContenedorFragment());
        } else if (id == R.id.nav_gallery) {

            cambiarDeFragment(new RedFragment());

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void cambiarDeFragment(Fragment fragmentAcambiar) {
        getSupportFragmentManager().beginTransaction().replace(R.id.contedor_fragmento,fragmentAcambiar).commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    public void enviarNumero(String numero) {

        Bundle paquete=new Bundle();
        paquete.putString("numero",numero);
        //cargo el fragment en el Activity

        Intent intent = new Intent(MainActivity.this, ActivityDescripcionNoticia.class);
        intent.putExtras(paquete);
        startActivity(intent);

    }
}
