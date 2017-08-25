package com.labsangelquinoch.animacionesrandom;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.net.Uri;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements PrimerFragment.OnFragmentInteractionListener, TercerFragment.OnFragmentInteractionListener, SegundoFragment.OnFragmentInteractionListener, View.OnClickListener {

    private SectionsPagerAdapter mSectionsPagerAdapter;

    private ViewPager mViewPager;
    private static Toolbar toolbar;
    private AppBarLayout appBarLayout;
    private TabLayout pagerTabStrip;
    private FloatingActionButton fab,op1,op2,op3;
    Animation FabOpen,FabClose,FabRClockwise,FabRanticlockwise;
    boolean isOpen=false; //usaremos esta variable para los fab buttons
    private ImageView icono;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        appBarLayout=(AppBarLayout)findViewById(R.id.appbar);
        pagerTabStrip=(TabLayout) findViewById(R.id.tabop);
        setSupportActionBar(toolbar);
        icono=(ImageView)findViewById(R.id.icono);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());


        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        pagerTabStrip.setupWithViewPager(mViewPager);


        fab = (FloatingActionButton) findViewById(R.id.fab);
        op1=(FloatingActionButton)findViewById(R.id.op1);
        op2=(FloatingActionButton)findViewById(R.id.op2);
        op3=(FloatingActionButton)findViewById(R.id.op3);
        FabOpen = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fab_open);
        FabClose= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fab_close);
        FabRClockwise= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate_clockwise);
        FabRanticlockwise= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate_anticlockwise);
        fab.setOnClickListener(this);
        op1.setOnClickListener(this);
        op2.setOnClickListener(this);
        op3.setOnClickListener(this);
        CoordinatorLayout coordinatorLayout = (CoordinatorLayout) findViewById(R.id.main_content);

        /*
        AnimationDrawable animationDrawable = (AnimationDrawable)coordinatorLayout.getBackground();
        //esta anterior enlaza el animationDrawable con el Background del coodinatorLayout
        animationDrawable.setEnterFadeDuration(2500);//la duración de entrada del fade
        animationDrawable.setExitFadeDuration(5000);
        CollapsingToolbarLayout collapsingToolbarLayout= (CollapsingToolbarLayout)findViewById(R.id.toolbar_layout);
        AnimationDrawable animationDrawable2 = (AnimationDrawable)collapsingToolbarLayout.getContentScrim();
        animationDrawable2.setEnterFadeDuration(2500);
        animationDrawable2.setExitFadeDuration(5000);
        animationDrawable2.start();
        animationDrawable.start();*/ //iniciamos la animación del background

        //Ahora realizamos el cambio del color del AppBar y el pagerTab
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {//En este método obtenemos com parámetro la posición
                //Con la posición hacemos un if (podíamos usar un case) para cambiar el color del AppBar y el PagerTab
                //según el fragmento en el que estemos
                /*
                if(position==0){
                    appBarLayout.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    pagerTabStrip.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    icono.setBackground(getResources().getDrawable(R.drawable.paso1));
                }
                if(position==1){
                    appBarLayout.setBackgroundColor(getResources().getColor(R.color.amarillodark));
                    pagerTabStrip.setBackgroundColor(getResources().getColor(R.color.amarillodark));
                    icono.setBackground(getResources().getDrawable(R.drawable.paso2));
                }
                if(position==2){
                    appBarLayout.setBackgroundColor(getResources().getColor(R.color.verde1));
                    pagerTabStrip.setBackgroundColor(getResources().getColor(R.color.verde1));
                    icono.setBackground(getResources().getDrawable(R.drawable.paso3));
                }
                if(position==3){
                    appBarLayout.setBackgroundColor(getResources().getColor(R.color.colorPrimary2));
                    pagerTabStrip.setBackgroundColor(getResources().getColor(R.color.colorPrimary2));
                    icono.setBackground(getResources().getDrawable(R.drawable.paso4));
                }*/
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.action_settings:
                Toast.makeText(getApplicationContext(),"Ajustes",Toast.LENGTH_LONG).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
           /* case R.id.fab:
                if(isOpen){
                    op1.startAnimation(FabClose);
                    op2.startAnimation(FabClose);
                    op3.startAnimation(FabClose);
                    fab.startAnimation(FabRanticlockwise);
                    op1.setClickable(false);
                    op2.setClickable(false);
                    op3.setClickable(false);
                    isOpen=false;
                }
                else{
                    op1.startAnimation(FabOpen);
                    op2.startAnimation(FabOpen);
                    op3.startAnimation(FabOpen);
                    fab.startAnimation(FabRClockwise);
                    op1.setClickable(true);
                    op2.setClickable(true);
                    op3.setClickable(true);
                    isOpen=true;
                }
                break;*/
            case R.id.op1:
                Toast.makeText(getApplicationContext(),"Reemplaza con un intent a tu actividad",Toast.LENGTH_LONG).show();
                break;
            case R.id.op2:
                Toast.makeText(getApplicationContext(),"La informática es el arte de controlar la complejidad",Toast.LENGTH_LONG).show();
                break;
            case R.id.op3:
                Intent faceIntent= getOpenFacebookIntent(MainActivity.this);
                startActivity(faceIntent);
                break;
        }
    }
    public static Intent getOpenFacebookIntent(Context context) {//este método abre nuestra página de facebook
        try {
            context.getPackageManager().getPackageInfo("com.facebook.katana", 0);
            return new Intent(Intent.ACTION_VIEW, Uri.parse("fb://page/165373163880374"));
        } catch (Exception e) {
            return new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/cotillones360"));
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
                    fragment=new PrimerFragment();
                    break;
                case 2:
                    fragment=new SegundoFragment();
                    break;
                case 3:
                    fragment=new TercerFragment();
                    break;
                case 4:
                    fragment=new TercerFragment();
                    break;
            }
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);

            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_home, container, false);
           // TextView textView = (TextView) rootView.findViewById(R.id.section_label);
           // textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
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
            return 4;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "GATO 0";
                case 1:
                    return "GATO 1";
                case 2:
                    return "GATO 2";
                case 3:
                    return "GATO 3";
            }
            return null;
        }
    }
}
