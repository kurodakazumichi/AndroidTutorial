package com.example.mylistview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.LiveFolders;
import android.support.v4.app.ShareCompat;
import android.support.v4.view.LayoutInflaterCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ListViewCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class MainActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		ListView myListView = (ListView) findViewById(R.id.myListView);

		// データ作成
		ArrayList<User> items = new ArrayList<>();

		items.add(new User(R.mipmap.ic_launcher, "Kuroda", "大阪市"));
		items.add(new User(R.mipmap.ic_launcher, "Hirata", "大阪市"));
		items.add(new User(R.mipmap.ic_launcher, "Daniera", "宝塚市"));

		// アダプター作成
		UserAdapter adapter = new UserAdapter(this, R.layout.list_view, items);

		// リストビューに表示
		myListView.setEmptyView(findViewById(R.id.emptyView));
		myListView.setAdapter(adapter);

		// リストアイテムがタップされたときのイベント
		myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(
					AdapterView<?> parent, // adapter
					View view,             // タップされたView
					int position,          // 何番目
					long id)               // ViewのID
			{
				TextView name = (TextView)view.findViewById(R.id.name);
				Toast.makeText(MainActivity.this, Integer.toString(position) + ":" + name.getText().toString(), Toast.LENGTH_SHORT).show();
				name.setText("Tapped");
			}
		});
	}

	public class UserAdapter extends ArrayAdapter<User> {
		private LayoutInflater layoutInflater;

		public UserAdapter(Context c, int id, ArrayList<User> users) {
			super(c, id, users);
			this.layoutInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		}

		@Override
		public View getView(int pos, View convertView, ViewGroup parent) {
			ViewHolder holder;
			if (convertView == null) {
				convertView = layoutInflater.inflate(R.layout.list_view, parent, false);

				holder = new ViewHolder();
				holder.icon = ((ImageView) convertView.findViewById(R.id.icon));
				holder.name = ((TextView) convertView.findViewById(R.id.name));
				holder.loc = ((TextView) convertView.findViewById(R.id.loc));
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder)convertView.getTag();
			}

			User user = (User) getItem(pos);

			holder.icon.setImageBitmap(user.getIcon());
			holder.name.setText(user.getName());
			holder.loc.setText(user.getLoc());
			return convertView;
		}
	}

	static class ViewHolder {
		ImageView icon;
		TextView name;
		TextView loc;
	}

	/**
	 * ユーザーデータをまとめたクラス
	 */
	public class User {
		private Bitmap icon;
		private String name;
		private String loc;

		public User(int icon, String name, String loc) {
			this.icon = BitmapFactory.decodeResource(getResources(), icon);
			this.name = name;
			this.loc = loc;
		}

		public Bitmap getIcon() {
			return icon;
		}

		public void setIcon(Bitmap icon) {
			this.icon = icon;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getLoc() {
			return loc;
		}

		public void setLoc(String loc) {
			this.loc = loc;
		}
	}
}
