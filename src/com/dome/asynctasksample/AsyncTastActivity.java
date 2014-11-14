package com.dome.asynctasksample;

import java.lang.ref.SoftReference;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListView;

/*AsyncTask的特点是任务在主线程之外运行，而回调方法是在主线程中执行，这就有效地避免了使用Handler带来的麻烦。阅读 AsyncTask的源码可知，
 * AsyncTask是使用java.util.concurrent 框架来管理线程以及任务的执行的，concurrent框架是一个非常 成熟，高效的框架，经过了严格的测试。
 * 这说明AsyncTask的设计很好的解决了匿名线程存在的问题。 AsyncTask是抽象类，其结构图如下图所示： AsyncTask定义了三种泛型类型 Params，Progress和Result。 
 * Params 启动任务执行的输入参数，比如HTTP请求的URL。 Progress 后台任务执行的百分比。 Result 后台执行任务最终返回的结果，比如String。 
 * 子类必须实现抽象方法doInBackground(Params… p) ，在此方法中实现任务的执行工作，比如连接网络获取数据等。通常还应 该实现onPostExecute(Result r)方法，
 * 因为应用程序关心的结果在此方法中返回。需要注意的是AsyncTask一定要在主线程中创 建实例。 AsyncTask的执行分为四个步骤，每一步都对应一个回调方法，需要注意的是这些方法不应该由应用程序调用，
 * 开发者需要做的 就是实现这些方法。在任务的执行过程中，这些方法被自动调用，运行过程，如下图所示： onPreExecute() 当任务执行之前开始调用此方法，可以在这里显示进度对话框。 
 * doInBackground(Params…) 此方法在后台线程执行，完成任务的主要工作，通常需要较长的时间。在执行过程中可以调用 publicProgress(Progress…)来更新任务的进度。 
 * onProgressUpdate(Progress…) 此方法在主线程执行，用于显示任务执行的进度。 onPostExecute(Result) 此方法在主线程执行，任务执行的结果作为此方法的参数返回
 */

public class AsyncTastActivity extends Activity{

	private ListView listView;
	
	private List<String> urlList;
	private ArrayList<HashMap<String, Object>> itemList;
	
	private ImageAdapter listItemAdapter;
	
	@SuppressWarnings("unchecked")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO 自动生成的方法存根
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_item);
		
		listView = (ListView)findViewById(R.id.listview);

        urlList = new ArrayList<String>();
        urlList.add("http://www.baidu.com/img/baidu_sylogo1.gif");
        urlList.add("http://y2.ifengimg.com/2012/06/24/23063562.gif");
        urlList.add("http://himg2.huanqiu.com/statics/images/index/logo.png");
        
        itemList = new ArrayList<>();
        
        listItemAdapter = new ImageAdapter(this, itemList);
        listView.setAdapter(listItemAdapter);
        
        AsyncTask<List<String>, Integer, Hashtable<String, SoftReference<Drawable>>> task = new AsyncTask<List<String>, 
        		Integer, Hashtable<String,SoftReference<Drawable>>>() {

					@Override
					protected Hashtable<String, SoftReference<Drawable>> doInBackground(
							List<String>... params) {
						// TODO 自动生成的方法存根
						Hashtable<String, SoftReference<Drawable>> table = new Hashtable<String, SoftReference<Drawable>>();
						List<String> imageUriList = params[0];
						for (String urlStr : imageUriList) {
							try {
								URL url = new URL(urlStr);
								Drawable drawable = Drawable.createFromStream(url.openStream(), "src");
						        table.put(urlStr, new SoftReference<Drawable>(drawable));
						    } catch (Exception e) {
						    	e.printStackTrace();
						    }
						}
						return table;
					}

					@Override
					protected void onPostExecute(
						Hashtable<String, SoftReference<Drawable>> result) {
						super.onPostExecute(result);
						Collection<SoftReference<Drawable>> col = result.values();
						for (SoftReference<Drawable> ref : col) {
					    	HashMap<String, Object> map = new HashMap<String, Object>();
					    	map.put("ItemImage", ref.get());
					    	itemList.add(map);
					    }
						listItemAdapter.notifyDataSetChanged();
					}
        };

		task.execute(urlList);
	}

}
