/*
 * THEORY OF COMPUTATION - HW3
 * 資工系104乙班
 * 鄭文
 * F74004012
*/

import java.net.*;
import java.io.*;
import org.json.*;


public class Hw4 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if(args.length != 1)
		{
			System.out.println("格式錯誤~~~");
			return;
		}
		String myUrl=args[0];
		
		//string for 路，街，巷
		String []road;	
		String []broad;
		String []street;
		String []alley;
		boolean namesame=false;
		boolean yearsame=false;
		String []ryear;
		String []byear;
		String []syear;
		String []ayear;
		int []rtimes;
		int []btimes;
		int []stimes;
		int []atimes;
		int tmpr=0,tmpb=0,tmps=0,tmpa=0,whichroad=0;
		
		try{
		URL MyURLData = new URL(myUrl);
		Reader MyURLReader = new BufferedReader(new InputStreamReader(MyURLData.openStream(),"UTF-8"));	
		JSONTokener MyJson = new JSONTokener(MyURLReader);
		JSONArray MyJsonArray = new JSONArray(MyJson);
		JSONObject MyJsonObject;
		
		road = new String [MyJsonArray.length()];
		broad = new String [MyJsonArray.length()];
		street = new String [MyJsonArray.length()];
		alley = new String [MyJsonArray.length()];
		ryear = new String [MyJsonArray.length()];
		byear = new String [MyJsonArray.length()];
		syear = new String [MyJsonArray.length()];
		ayear = new String [MyJsonArray.length()];
		rtimes = new int [MyJsonArray.length()];
		btimes = new int [MyJsonArray.length()];
		stimes = new int [MyJsonArray.length()];
		atimes = new int [MyJsonArray.length()];
		
		for (int i = 0; i < MyJsonArray.length(); i++)
		{
			MyJsonObject = MyJsonArray.getJSONObject(i);
			//if (!MyJsonObject.getString("鄉鎮市區").equals(Area)) continue;
			//System.out.println(MyJsonObject.getString("土地區段位置或建物區門牌"));
			if (MyJsonObject.getString("土地區段位置或建物區門牌").contains("路")&& !MyJsonObject.getString("土地區段位置或建物區門牌").contains("街") && !MyJsonObject.getString("土地區段位置或建物區門牌").contains("巷") && !MyJsonObject.getString("土地區段位置或建物區門牌").contains("大道"))
			{
				String []tmp=MyJsonObject.getString("土地區段位置或建物區門牌").split("路");
				//tmp[0]=tmp[0]+"路";
				namesame=false;
				for(int j=0;j<tmpr;j++)
				{
					if(tmp[0].equals(road[j])){ namesame = true;	whichroad = j; break;}
				}
				
				if(!namesame){
				road[tmpr]=tmp[0];
				rtimes[tmpr]++;
				ryear[tmpr]=MyJsonObject.getInt("交易年月")+" ";
				tmpr++;
				}
				else if(namesame)
				{
					String []tmpnums=ryear[whichroad].split(" ");
					yearsame=false;
					for(int k=0; k<tmpnums.length ;k++)
					{
						int year1=Integer.parseInt(tmpnums[k]);
						int year2=MyJsonObject.getInt("交易年月");
						if(year1==year2){	yearsame=true;}
					}
					if(!yearsame)
					{
						rtimes[whichroad]++;
						ryear[whichroad]=MyJsonObject.getInt("交易年月")+" ";
					}
				}
			}
			else if (MyJsonObject.getString("土地區段位置或建物區門牌").contains("大道") && !MyJsonObject.getString("土地區段位置或建物區門牌").contains("街") && !MyJsonObject.getString("土地區段位置或建物區門牌").contains("巷"))
			{
				//here !!!!!!!!!!!!!!!!!!!!!!!!!!!
				String []tmp=MyJsonObject.getString("土地區段位置或建物區門牌").split("大道");
				//tmp[0]=tmp[0]+"路";
				namesame=false;
				for(int j=0;j<tmpb;j++)
				{
					if(tmp[0].equals(broad[j])){ namesame = true;	whichroad = j; break;}
				}
				
				if(!namesame){
				broad[tmpb]=tmp[0];
				btimes[tmpb]++;
				byear[tmpb]=MyJsonObject.getInt("交易年月")+" ";
				tmpb++;
				}
				else if(namesame)
				{
					String []tmpnums=byear[whichroad].split(" ");
					yearsame=false;
					for(int k=0; k<tmpnums.length ;k++)
					{
						int year1=Integer.parseInt(tmpnums[k]);
						int year2=MyJsonObject.getInt("交易年月");
						if(year1==year2){	yearsame=true;}
					}
					if(!yearsame)
					{
						btimes[whichroad]++;
						byear[whichroad]=MyJsonObject.getInt("交易年月")+" ";
					}
				}
			}
			else if (MyJsonObject.getString("土地區段位置或建物區門牌").contains("街") && !MyJsonObject.getString("土地區段位置或建物區門牌").contains("巷"))
			{
				//here !!!!!!!!!!!!!!!!!!!!!!!!!!!
				String []tmp=MyJsonObject.getString("土地區段位置或建物區門牌").split("街");
				//tmp[0]=tmp[0]+"路";
				namesame=false;
				for(int j=0;j<tmps;j++)
				{
					if(tmp[0].equals(street[j])){ namesame = true;	whichroad = j; break;}
				}
				
				if(!namesame){
				street[tmps]=tmp[0];
				stimes[tmps]++;
				syear[tmps]=MyJsonObject.getInt("交易年月")+" ";
				tmps++;
				}
				else if(namesame)
				{
					String []tmpnums=syear[whichroad].split(" ");
					yearsame=false;
					for(int k=0; k<tmpnums.length ;k++)
					{
						int year1=Integer.parseInt(tmpnums[k]);
						int year2=MyJsonObject.getInt("交易年月");
						if(year1==year2){	yearsame=true;}
					}
					if(!yearsame)
					{
						stimes[whichroad]++;
						syear[whichroad]=MyJsonObject.getInt("交易年月")+" ";
					}
				}
			}
			else if (MyJsonObject.getString("土地區段位置或建物區門牌").contains("巷"))
			{
				//need to change hereeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee
				String []tmp=MyJsonObject.getString("土地區段位置或建物區門牌").split("巷");
				//tmp[0]=tmp[0]+"路";
				namesame=false;
				for(int j=0;j<tmpa;j++)
				{
					if(tmp[0].equals(alley[j])){ namesame = true;	whichroad = j; break;}
				}
				
				if(!namesame){
				alley[tmpa]=tmp[0];
				atimes[tmpa]++;
				ayear[tmpa]=MyJsonObject.getInt("交易年月")+" ";
				tmpa++;
				}
				else if(namesame)
				{
					String []tmpnums=ayear[whichroad].split(" ");
					yearsame=false;
					for(int k=0; k<tmpnums.length ;k++)
					{
						int year1=Integer.parseInt(tmpnums[k]);
						int year2=MyJsonObject.getInt("交易年月");
						if(year1==year2){	yearsame=true;}
					}
					if(!yearsame)
					{
						atimes[whichroad]++;
						ayear[whichroad]=MyJsonObject.getInt("交易年月")+" ";
					}
				}
			}	
		}//end for
		
		int max=0,state=0,num=0;
		for(int i=0;i<tmpr;i++)
		{
			if(max<rtimes[i]){max=rtimes[i];	state=1;	num=i;}
		}
		for(int i=0;i<tmpb;i++)
		{
			if(max<btimes[i]){max=btimes[i];	state=2;	num=i;}
		}
		for(int i=0;i<tmps;i++)
		{
			if(max<stimes[i]){max=stimes[i];	state=3;	num=i;}
		}
		for(int i=0;i<tmpa;i++)
		{
			if(max<atimes[i]){max=atimes[i];	state=4;	num=i;}
		}
		
		if(state==1){
			int damnprice=0,suckprice=999999999;
			for(int i=0;i< MyJsonArray.length(); i++)
			{
				MyJsonObject =MyJsonArray.getJSONObject(i);
				if(MyJsonObject.getString("土地區段位置或建物區門牌").contains(road[num]))
				{
					if(MyJsonObject.getInt("總價元")>damnprice){damnprice=MyJsonObject.getInt("總價元");}
					if(MyJsonObject.getInt("總價元")<suckprice){suckprice=MyJsonObject.getInt("總價元");}
				}
			}
			System.out.println(road[num]+"路, 最高成交價:"+damnprice+", 最低成交價:"+suckprice);
		}
		else if(state==2){
			int damnprice=0,suckprice=999999999;
			for(int i=0;i< MyJsonArray.length(); i++)
			{
				MyJsonObject =MyJsonArray.getJSONObject(i);
				if(MyJsonObject.getString("土地區段位置或建物區門牌").contains(broad[num]))
				{
					if(MyJsonObject.getInt("總價元")>damnprice){damnprice=MyJsonObject.getInt("總價元");}
					if(MyJsonObject.getInt("總價元")<suckprice){suckprice=MyJsonObject.getInt("總價元");}
				}
			}
			System.out.println(broad[num]+"大道, 最高成交價:"+damnprice+", 最低成交價:"+suckprice);
		}
		else if(state==3){
			int damnprice=0,suckprice=999999999;
			for(int i=0;i< MyJsonArray.length(); i++)
			{
				MyJsonObject =MyJsonArray.getJSONObject(i);
				if(MyJsonObject.getString("土地區段位置或建物區門牌").contains(street[num]))
				{
					if(MyJsonObject.getInt("總價元")>damnprice){damnprice=MyJsonObject.getInt("總價元");}
					if(MyJsonObject.getInt("總價元")<suckprice){suckprice=MyJsonObject.getInt("總價元");}
				}
			}
			System.out.println(street[num]+"街, 最高成交價:"+damnprice+", 最低成交價:"+suckprice);
		}
		else if(state==4){
			int damnprice=0,suckprice=999999999;
			for(int i=0;i< MyJsonArray.length(); i++)
			{
				MyJsonObject =MyJsonArray.getJSONObject(i);
				if(MyJsonObject.getString("土地區段位置或建物區門牌").contains(alley[num]))
				{
					if(MyJsonObject.getInt("總價元")>damnprice){damnprice=MyJsonObject.getInt("總價元");}
					if(MyJsonObject.getInt("總價元")<suckprice){suckprice=MyJsonObject.getInt("總價元");}
				}
			}
			System.out.println(alley[num]+"巷, 最高成交價:"+damnprice+", 最低成交價:"+suckprice);
		}

		}catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
