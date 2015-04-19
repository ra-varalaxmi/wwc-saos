package android.saos.wwc.com.smallactsofsweetness;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class StatusActivity extends ActionBarActivity {

	private RecyclerView recentActivityView;
	RecentActivityAdapter adapter;

	TextView donationCountView;
	TextView milestoneCountView;

	private static final long HOUR_IN_MILLIS = 60 * 60 * 1000;
	private static final long DAY_IN_MILLIS = 24 * HOUR_IN_MILLIS;

	private List<ActivityInfo> getDummyActivity() {
		long now = System.currentTimeMillis();
		List<ActivityInfo> activity = new ArrayList<ActivityInfo>();
		activity.add(new ActivityInfo(ActivityInfo.Type.MILESTONE, "Congratulations!", "Lorem ipsum", now));
		activity.add(new ActivityInfo(ActivityInfo.Type.REDEEM, "Thank you!", "Your gift to has been redeemed", now - HOUR_IN_MILLIS));
		activity.add(new ActivityInfo(ActivityInfo.Type.DONATION, "Lorem ipsum", "Lorem ipsum", now - HOUR_IN_MILLIS*2));
		activity.add(new ActivityInfo(ActivityInfo.Type.REDEEM, "Thank you!", "Your gift to has been redeemed", now - HOUR_IN_MILLIS*3));
		activity.add(new ActivityInfo(ActivityInfo.Type.DONATION, "Lorem ipsum", "Lorem ipsum", now - DAY_IN_MILLIS));
		activity.add(new ActivityInfo(ActivityInfo.Type.DONATION, "Lorem ipsum", "Lorem ipsum", now - DAY_IN_MILLIS));
		activity.add(new ActivityInfo(ActivityInfo.Type.REDEEM, "Thank you!", "Your gift to has been redeemed", now - DAY_IN_MILLIS*3));
		activity.add(new ActivityInfo(ActivityInfo.Type.DONATION, "Some bakery", "Lorem ipsum", now - DAY_IN_MILLIS*4));
		return activity;
	}

	private String getDummyDonationCount() {
		return "4";
	}

	private String getDummyMilestoneCount() {
		return "1";
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.status);

		recentActivityView = (RecyclerView) findViewById(R.id.activity_list);
		recentActivityView.setHasFixedSize(true);
		recentActivityView.setLayoutManager(new LinearLayoutManager(this));

		adapter = new RecentActivityAdapter(this, getDummyActivity());
		recentActivityView.setAdapter(adapter);

		donationCountView = (TextView) findViewById(R.id.donation_count);
		donationCountView.setText(getDummyDonationCount());

		milestoneCountView = (TextView) findViewById(R.id.milestone_count);
		milestoneCountView.setText(getDummyMilestoneCount());
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_status, menu);
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
}
