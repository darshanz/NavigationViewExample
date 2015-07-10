package me.game.navigationviewexample;

import android.net.Uri;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import me.game.navigationviewexample.fragments.HomeFragment;
import me.game.navigationviewexample.fragments.InboxFragment;
import me.game.navigationviewexample.fragments.SentFragment;
import me.game.navigationviewexample.interfaces.OnFragmentInteractionListener;

public class MainActivity extends AppCompatActivity implements OnFragmentInteractionListener {



    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;
    private Toolbar mToolbar;

    private FragmentManager mFragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mDrawerLayout = (DrawerLayout)findViewById(R.id.drawerLayout);
        mNavigationView = (NavigationView)findViewById(R.id.navigationView);


        final View view = getLayoutInflater().inflate(R.layout.layout_drawer_header, mNavigationView, false);

        mNavigationView.addHeaderView(view);

        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.open, R.string.close);

        mDrawerLayout.setDrawerListener(drawerToggle);
        drawerToggle.syncState();

        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                if(menuItem.getItemId() == R.id.action_inbox){

                    mFragmentManager.beginTransaction()
                            .add(R.id.container, InboxFragment.newInstance("", ""))
                            .addToBackStack(null)
                            .commit();

                    mDrawerLayout.closeDrawer(mNavigationView);

                   // Snackbar.make(view, "You Selected Inbox", Snackbar.LENGTH_LONG).show();
                }else{
                    mFragmentManager.beginTransaction().add(R.id.container, SentFragment.newInstance("", "")).commit();
                   // Snackbar.make(view, "You Selected Send", Snackbar.LENGTH_LONG).show();
                    mDrawerLayout.closeDrawer(mNavigationView);

                }

                return false;
            }
        });

        mFragmentManager = getSupportFragmentManager();

        //THis fragment can also be loaded initially through xml

        //mFragmentManager.beginTransaction().add(R.id.container, HomeFragment.newInstance("","")).commit();

    }


    @Override
    public void onFragmentInteraction(String btnType) {
        if(btnType.equals("Sent")){
            mFragmentManager.beginTransaction().add(R.id.container, SentFragment.newInstance("", "")).commit();
        }

    }
}
