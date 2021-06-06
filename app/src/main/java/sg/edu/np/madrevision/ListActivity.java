package sg.edu.np.madrevision;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Random;

public class ListActivity extends AppCompatActivity {
    // Toggling the Follow button should save the state back in the respective User object.
    static ArrayList<User> userList;

    DBHandler dbHandler = new DBHandler(this, null,null,1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        /*  Practical 3
        //Create an onClickListener for the image created in previous step.
        // Upon clicking the image, an AlertDialog will appear
        ImageView img = findViewById(R.id.imageView);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Debug","Image clicked");

                new AlertDialog.Builder(ListActivity.this)
                        .setTitle("Profile")
                        .setMessage("MADness")
                        .setCancelable(false)
                        .setPositiveButton("View", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent viewProfile = new Intent(ListActivity.this, MainActivity.class); //redirect to main activity page
                                viewProfile.putExtra("id", new Random().nextInt()); //Upon clicking the View button, a random integer will be generated. and the random integer is sent over.
                                startActivity(viewProfile); //start intent
                            }
                        })
                        .setNegativeButton("Close",null)
                        .show();    //show alert dialog
            }
        });

         */


        //Create a List of 20 User objects in the ListActivity.
        // Randomize the name, descriptions and value of followed.

        //practical 6
        //Create your db handler class extending SQLiteOpenHelper.
        // This class will create a new database upon initialization.
        // There is a User table, whose structure follows the User class diagram.
        // 20 User data are generated and inserted into the table. The name, description
        // and value of followed are randomized, the value of id is an auto-increment primary key.
        /*
        userList = new ArrayList<>();
        for(int i=0; i<20; i++){
            User u = new User();
            u.name = "Name" + randomNum();
            u.description = "Description" + randomNum();
            u.followed = new Random().nextInt()%2 == 0; //0=0 true
            userList.add(u);
        }

         */

        /*
        for (User u : userList) {
            dbHandler.addUser(u);
        }

         */

        //Modify your RecyclerView so that it is pre-populated
        // with information from the database only.
        userList = dbHandler.getUsers();


        //Replace the ImageView in the ListActivity with a RecyclerView.
        //Populate the RecyclerView with the list created in previous step.
        //ItemsAdapter, ItemViewHolder, rv_item and rv_item2
        RecyclerView rv = findViewById(R.id.recyclerView);
        ItemsAdapter itemsAdapter = new ItemsAdapter(userList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(linearLayoutManager);
        rv.setItemAnimator(new DefaultItemAnimator());
        rv.setAdapter(itemsAdapter);
    }

    public int randomNum(){
        return new Random().nextInt();
    }
}