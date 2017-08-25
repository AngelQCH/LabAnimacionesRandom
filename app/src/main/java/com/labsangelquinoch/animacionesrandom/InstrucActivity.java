package com.labsangelquinoch.animacionesrandom;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

public class InstrucActivity extends AppCompatActivity implements Instruc1Fragment.OnFragmentInteractionListener,Instruc2Fragment.OnFragmentInteractionListener, Instruc3Fragment.OnFragmentInteractionListener, View.OnClickListener {

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    private Button next,saltar;
    private int posicion=1;
    //ahora para las shared preferences
    private CheckBox edt;
    boolean datos=false;

    private SharedPreferences sharedPreferences;
    private String mostrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instruc);
        sharedPreferences = getSharedPreferences("mostrarono", Context.MODE_PRIVATE);
        mostrar=leerPreferences();
        next=(Button)findViewById(R.id.next);
        saltar=(Button)findViewById(R.id.saltar);
        next.setOnClickListener(this);
        saltar.setOnClickListener(this);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        if(!leerPreferences().equals("")){
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                //una noche de desvelada, (previamente la tarde), next all day hasta las las 06:52 de la tarde
                //pero valió la pena, está hermoso
                if(position==0){
                    posicion=1;
                }
                if(position==1){
                    posicion=2;
                }
                if(position==2){
                    posicion=3;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
    public void showChangeLangDialog() {

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.custom_dialog, null);//le enviamos nuestro propio dialog
        dialogBuilder.setView(dialogView);

        edt = (CheckBox) dialogView.findViewById(R.id.mostrar);
        //aceptar=(Button)dialogView.findViewById(R.id.aceptar);
        dialogBuilder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                guardarPreferences();
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
            }
        });
        AlertDialog b = dialogBuilder.create();
        b.show();
    }
    public void guardarPreferences() {
        //obtener valor1, valor 2 y guardarlo
        String valor1 = "";
        if(edt.isChecked()){
            valor1="no";
        }
        //para guardar en formato clave valor tenemos que llamar al objeto SharedPreferces
        SharedPreferences.Editor editorprefe = sharedPreferences.edit();//creamos un objeto de tipos SP pero que pueda editarse
        editorprefe.putString("mostrarsiono", valor1);
        //ahora usamos un commit para guardar los cambios
        editorprefe.commit();
        edt.setText("");
    }

    public String leerPreferences() {
        String valor1=sharedPreferences.getString("mostrarsiono", "");//obtenermos un datos llamado "v1" del objeto sharedPreferences, si no hay mostrar ""
        return valor1;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 1: {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.e("Permiso", "Concedido");
                } else {
                    Log.e("Permiso", "Denegado");
                }
                return;
            }
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.saltar:
                showChangeLangDialog();
                break;
            case R.id.next:
                if(posicion==3){
                    showChangeLangDialog();
                }
                mViewPager.setCurrentItem(posicion);

                break;

        }
    }


    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static Fragment newInstance(int sectionNumber) {
            Fragment fragment=null;
            switch (sectionNumber){
                case 1:
                    fragment=new Instruc1Fragment();
                    break;
                case 2:
                    fragment=new Instruc2Fragment();
                    break;
                case 3:
                    fragment=new Instruc3Fragment();
                    break;
            }
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);

            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_instruc2, container, false);
            return rootView;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "SECTION 1";
                case 1:
                    return "SECTION 2";
                case 2:
                    return "SECTION 3";
            }
            return null;
        }
    }
}
