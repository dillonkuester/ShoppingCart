package edu.fvtc.shoppingcart;

import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;


import java.io.File;
import java.util.ArrayList;



public class MainActivity extends AppCompatActivity {

    private static final String TAG = "myDebug";
    ArrayList<Item> items = new ArrayList<Item>();
    ListView lvItems;
    ArrayAdapter arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        items.add(new Item("Ham"));
        items.add(new Item("Eggs", true));
        items.add(new Item("Toast"));
        items.add(new Item("Hash Browns", true));
        items.add(new Item("Bacon", true));
        items.add(new Item("Salsa", true));


        Button btn = (Button) findViewById(R.id.itembutton);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.i("ItemButtonClick", "clicked the button");
                AddAnimalDialog();
            }
        });

        lvItems = findViewById(R.id.list);

        lvItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent,
                                    View view, int position, long id) {
                // When I click, toggle the checkbox
                items.get(position).isChecked = !(items.get(position).isChecked);
                Log.println(Log.DEBUG, TAG, items.get(position).name + ":" + items.get(position).isChecked);
            }
        });

        ArrayAdapter<Item> arrayAdapter = new ArrayAdapter<Item>(this, android.R.layout.simple_list_item_multiple_choice, items);

        lvItems.setAdapter(arrayAdapter);

        // Check the boxes that need to be checked
        for(int i=0; i < items.size(); i++)
        {
            lvItems.setItemChecked(i, items.get(i).isChecked);
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
        //added the if for write
        if (id == R.id.action_write) {

            FileIO fileIO = new FileIO();
            fileIO.WriteFile(this, items);
            return true;
        }
        if (id == R.id.action_read) {

            FileIO fileIO = new FileIO();
            fileIO.ReadFile(this);
        //    Log.println(Log.DEBUG, TAG, "File Read!!!!!!!!!");
            return true;
        }
        if (id == R.id.action_additem)
        {
            Log.i("ItemButtonClick", "clicked the button");
            AddAnimalDialog();
            return true;
        }
        if (id == R.id.action_refresh)
        {
            arrayAdapter.notifyDataSetChanged();
        }
        if (id == R.id.action_removeall)
        {

        }
        if (id == R.id.action_removechecked)
        {
            //removes the checkedboxes etc
            lvItems.invalidateViews();
        }



        return super.onOptionsItemSelected(item);
    }

    private void AddAnimalDialog()
    {
        Log.println(Log.DEBUG, TAG, "Start");

        LayoutInflater inflater = LayoutInflater.from(this);

        final View addItemView = inflater.inflate(R.layout.additem, null);

        final Context context = this;

        new AlertDialog.Builder(this)
                .setTitle("Add Item")
                .setView(addItemView)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    //finding an element on a view
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        EditText txtAddItem = (EditText)addItemView.findViewById(R.id.etNewItem);
                        String item = txtAddItem.getText().toString();
                        Log.println(Log.DEBUG, TAG, "Added " + item);

                        // TextView txtItems = (TextView)findViewById(R.id.Groceries);
                      //  txtItems.append("\n\n" + item);

                        items.add(new Item(item));

                        arrayAdapter = new ArrayAdapter<Item>(context, android.R.layout.simple_list_item_multiple_choice, items);
                        lvItems.setAdapter(arrayAdapter);

                        for (int i = 0; i < items.size(); i++)
                        {
                            lvItems.setItemChecked(i, items.get(i).isChecked);
                        }


                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.println(Log.DEBUG, TAG, "Cancel");

                    }
                }).show();


    }



}