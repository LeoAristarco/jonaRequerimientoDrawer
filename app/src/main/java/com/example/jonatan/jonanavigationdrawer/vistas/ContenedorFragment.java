package com.example.jonatan.jonanavigationdrawer.vistas;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.jonatan.jonanavigationdrawer.R;
import com.example.jonatan.jonanavigationdrawer.Utilidades;
import com.example.jonatan.jonanavigationdrawer.adapter.SeccionesAdapter;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ContenedorFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ContenedorFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ContenedorFragment extends Fragment {

    View vista;
    private AppBarLayout appBar;
    private TabLayout pestanas;
    private ViewPager viewPager;
    private SeccionesAdapter adapterSecciones;

    private OnFragmentInteractionListener mListener;

    public ContenedorFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ContenedorFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ContenedorFragment newInstance(String param1, String param2) {
        ContenedorFragment fragment = new ContenedorFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        vista = inflater.inflate(R.layout.fragment_contenedor, container, false);

        if (Utilidades.rotacion == 0) {
            View parent = (View) container.getParent();

            setearViewPager(parent);

        } else {
            Utilidades.rotacion = 1;
        }


        return vista;
    }

    private void setearViewPager(View parent) {
        if (appBar == null) {
            appBar = (AppBarLayout) parent.findViewById(R.id.appBar);
            pestanas = new TabLayout(getActivity());
            appBar.addView(pestanas);

            viewPager = (ViewPager) vista.findViewById(R.id.idViewPagerInformacion);

            llenarViewPager(viewPager);
            viewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                    super.onPageScrolled(position, positionOffset, positionOffsetPixels);
                }
            });
            pestanas.setupWithViewPager(viewPager);
        }
        pestanas.setTabGravity(TabLayout.GRAVITY_FILL);
    }


    private void llenarViewPager(ViewPager viewPager) {
        adapterSecciones =new SeccionesAdapter(getFragmentManager());
        adapterSecciones.addFragment(new FragmentList(),"Fragment Noticia");
        adapterSecciones.addFragment(new GreenFragment(),"Fragment Verde");

        viewPager.setAdapter(adapterSecciones);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (Utilidades.rotacion == 0) {
            appBar.removeView(pestanas);
        }
        Toast.makeText(getContext(), "onDestroyView De Contenedor Fragment", Toast.LENGTH_LONG).show();

/*        adapterSecciones.limpiarFragment();*/
        viewPager.removeAllViews();
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
