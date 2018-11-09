package edu.fvtc.shoppingcart;

import android.app.ListActivity;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Calendar;

public class FileIO {

    private static final String filename = "ShoppingList.txt";
    private static final String TAG = "myDebug";

    public String getFilename()
    {
        Calendar calNow = Calendar.getInstance();
        String filename = String.format("%d-%02d-%02d-%02d-%02d-%02d.txt",
                calNow.get(Calendar.YEAR),
                calNow.get(Calendar.MONTH),
                calNow.get(Calendar.DAY_OF_MONTH),
                calNow.get(Calendar.HOUR_OF_DAY),
                calNow.get(Calendar.MINUTE),
                calNow.get(Calendar.SECOND));

        return filename;
    }

    //passing the item from item class into our writefile method
    public void WriteFile(AppCompatActivity activity, ArrayList<Item> items)
    {
        try
        {

            OutputStreamWriter sw = new OutputStreamWriter(activity.openFileOutput(getFilename(), 0));
            int iCnt = 0;

            //foreach item in item
            for(Item item : items)
            {
                sw.write(item.name + "\r\n");
            }
            sw.close();

            Log.println(Log.DEBUG, TAG, "File written!!!!!!!!!");

        }
        catch(java.io.FileNotFoundException ex)
        {
            Toast.makeText(activity, "File exception", Toast.LENGTH_SHORT).show();
        }
        catch(Exception ex)
        {
            Toast.makeText(activity, ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public String ReadFile(AppCompatActivity activity) {
        String sRet = " ";

        try {
         //   Log.println(Log.DEBUG, TAG, "File Read!!!!!!!!!");

            InputStream is = activity.openFileInput(filename);
            Log.println(Log.DEBUG, TAG, "File Read!!!!!!!!!");

            if (is != null) {
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader br = new BufferedReader(isr);
                String sLine;
                StringBuffer sb = new StringBuffer();

                while ((sLine = br.readLine()) != null)
                    sb.append(sLine + "\r\n");

                is.close();

                sRet = sb.toString();
                Toast.makeText(activity, sRet, Toast.LENGTH_LONG).show();

                Log.println(Log.DEBUG, TAG, "File Read!!!!!!!!!");

            }
        }
        catch (java.io.FileNotFoundException e)
        {

        }
        catch (Exception ex)
        {
            Toast.makeText(activity, "Read Exception" + ex.getMessage(), Toast.LENGTH_LONG).show();
        }
        return sRet;
    }


}
