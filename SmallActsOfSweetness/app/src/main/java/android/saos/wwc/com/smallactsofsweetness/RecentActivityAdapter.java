package android.saos.wwc.com.smallactsofsweetness;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class RecentActivityAdapter extends RecyclerView.Adapter<RecentActivityAdapter.ViewHolder> {

	private List<ActivityInfo> activityInfos;

	private java.text.DateFormat timeFormat;
	private java.text.DateFormat dateFormat;

	public RecentActivityAdapter(Context context, List<ActivityInfo> dataset) {
		this.activityInfos = dataset;

		timeFormat = DateFormat.getTimeFormat(context);
		dateFormat = DateFormat.getDateFormat(context);
	}

	@Override
	public RecentActivityAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
		View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recent_item, viewGroup, false);
		return new ViewHolder(v);
	}

	@Override
	public void onBindViewHolder(RecentActivityAdapter.ViewHolder viewHolder, int position) {
		ActivityInfo info = activityInfos.get(position);

		int iconResId;
		switch (info.getType()) {
			case DONATION:
				iconResId = R.drawable.payment7;
				break;
			case REDEEM:
				iconResId = R.drawable.gift2;
				break;
			case MILESTONE:
				iconResId = R.drawable.first31;
				break;
			default:
				iconResId = R.mipmap.ic_launcher;
				break;
		}
		viewHolder.iconView.setImageResource(iconResId);
		viewHolder.titleView.setText(info.getTitle());
		viewHolder.messageView.setText(info.getMessage());
		viewHolder.dateView.setText(formatTimeStamp(info.getTimestamp()));

		viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				
			}
		});
	}

	@Override
	public int getItemCount() {
		return activityInfos.size();
	}

	private String formatTimeStamp(long timestamp) {
		String timestampLabel;
		Date old = new Date(timestamp);
		long now = System.currentTimeMillis();
		long gap = now - old.getTime();
		long midnight;

		final Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		midnight = calendar.getTimeInMillis();

		if (old.getTime() >= midnight) {
			timestampLabel = timeFormat.format(old);
		} else {
			timestampLabel = dateFormat.format(old);
		}
		return timestampLabel;
	}

	public static class ViewHolder extends RecyclerView.ViewHolder {

		ImageView iconView;
		TextView titleView;
		TextView messageView;
		TextView dateView;

		public ViewHolder(View itemView) {
			super(itemView);

			iconView = (ImageView) itemView.findViewById(R.id.recent_icon);
			titleView = (TextView) itemView.findViewById(R.id.recent_title);
			messageView = (TextView) itemView.findViewById(R.id.recent_message);
			dateView = (TextView) itemView.findViewById(R.id.recent_date);
		}
	}
}
