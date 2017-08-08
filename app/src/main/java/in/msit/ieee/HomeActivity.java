package in.msit.ieee;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import butterknife.OnClick;
import in.msit.ieee.fragments.AboutIEEEFragment;
import in.msit.ieee.fragments.AboutIEEEMSITFragment;
import in.msit.ieee.fragments.AndroidTeamFragment;
import in.msit.ieee.fragments.BranchAdvisorsFragment;
import in.msit.ieee.fragments.ContactUsFragment;
import in.msit.ieee.fragments.ExecutiveCommitteeFragment;
import in.msit.ieee.fragments.UpcomingEventsFragment;
import in.msit.ieee.fragments.WelcomeFragment;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final String BACK_STACK_ROOT_TAG = "root_fragment";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.popBackStack(BACK_STACK_ROOT_TAG, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        // Add the new tab fragment
        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container, new WelcomeFragment())
                .addToBackStack(BACK_STACK_ROOT_TAG)
                .commit();

        setTitle("");
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        displaySelectedPage(id);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void displaySelectedPage(int id) {
        Fragment fragment = null;

        if (id == R.id.nav_welcome) {
            fragment = new WelcomeFragment();
        } else if (id == R.id.nav_about_ieee) {
            fragment = new AboutIEEEFragment();
        } else if (id == R.id.nav_about_ieee_msit) {
            fragment = new AboutIEEEMSITFragment();
        } else if (id == R.id.nav_executive_committee) {
            fragment = new ExecutiveCommitteeFragment();
        } else if (id == R.id.nav_branch_advisors) {
            fragment = new BranchAdvisorsFragment();
        } else if (id == R.id.nav_upcoming_events) {
            fragment = new UpcomingEventsFragment();
        } else if (id == R.id.nav_contact_us) {
            fragment = new ContactUsFragment();
        } else if (id == R.id.nav_android_team) {
            fragment = new AndroidTeamFragment();
        }

        //replacing the fragment
        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.popBackStack(BACK_STACK_ROOT_TAG, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            FragmentTransaction ft = fragmentManager.beginTransaction();
            ft.replace(R.id.fragment_container, fragment);
            ft.addToBackStack(BACK_STACK_ROOT_TAG);
            ft.commit();
        }
    }
}
