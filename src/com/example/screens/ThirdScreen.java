package com.example.screens;

import java.util.Arrays;
import java.util.List;

import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.practice.R;

public class ThirdScreen extends Activity{
	
	ListView m_listView;
	
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.third_screen);
		
		List<String> m_objects =  Arrays.asList("Android", "iPhone", "WindowsMobile",
            "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
            "Linux", "OS/2", "Ubuntu", "Windows7", "Max OS X", "Linux",
            "OS/2", "Ubuntu", "Windows7", "Max OS X", "Linux", "OS/2",
            "Android", "iPhone", "WindowsMobile");
		
		m_listView = (ListView)findViewById(R.id.listView1);
		
		final CustomAdapter adapter = new CustomAdapter(this, R.layout.list_item, m_objects);
		
		
		
		m_listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, final View view,
	                int position, long id) {
				System.out.println("Paz");
				adapter.changeItemText(position, "Amitay");
				adapter.notifyDataSetChanged();
				
				
			}
			
		});
		
		m_listView.setAdapter(adapter);

	}
	
	public class CustomAdapter extends ArrayAdapter<String>
	{
		private List<String> m_objects;
		private Context m_context;

        public CustomAdapter(Context context, int resources, List <String> objects) {
			super(context, resources, objects);
			m_objects = objects;
			m_context = context;
		}
        
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

        	View view = convertView;
        	if(view == null){
        		LayoutInflater layoutInflator = (LayoutInflater)m_context.getSystemService(Service.LAYOUT_INFLATER_SERVICE);
        		view = layoutInflator.inflate(R.layout.list_item, null);
        	}
        	TextView textView = (TextView)view.findViewById(R.id.textView1);
        	
        	textView.setText(m_objects.get(position));
        	
        	return view;
        }

        @Override
        public String getItem(int index) {
              return m_objects.get(index);
        }

        @Override
        public int getCount() {
             return m_objects.size();
        }
        
        public void changeItemText(int index, String text)
        {
        	m_objects.set(index, text);
        }


	}

}
