package sg.edu.np.madrevision;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import static android.content.ContentValues.TAG;

public class MainActivity extends AppCompatActivity {
    User u;
    DBHandler dbHandler = new DBHandler(this,null,null,1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("Debug","create");

        Intent rec = getIntent();
        int value = rec.getIntExtra("id",0);
        /* practical 2
        //name and description are loaded from the User object.
        u = new User();
        u.name = "MAD";
        u.description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.";
        u.id = 1;
        u.followed = false;

         */
        u = ListActivity.userList.get(value);

        //Modify the MainActivity to display the random integer
        // together with the name.
        TextView name = findViewById(R.id.txtName);
        name.setText(u.name);

        TextView description = findViewById(R.id.txtDescription);
        description.setText(u.description);

        // The button on the left will show “Follow” if the
        // variable followed is false, and vice versa

        Button followButton = findViewById(R.id.btnFollow); //define follow button
        setFollowBtn();

        followButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v(TAG,"Follow Button Pressed!");
                u.followed = !u.followed;   //switch between true and false
                dbHandler.updateUser(u);

                if(u.followed){ //followed=true
                    followButton.setText("Unfollow");   //followed alr so button become unfollow
                    Toast.makeText(MainActivity.this,"Followed",Toast.LENGTH_SHORT).show(); //toast message show followed
                }
                else{
                    followButton.setText("Follow");
                    Toast.makeText(MainActivity.this,"Unfollowed",Toast.LENGTH_SHORT).show();
                }
            }
        });

        //setFollowBtn();//
    }


    private void setFollowBtn(){
        Button b = findViewById(R.id.btnFollow);
        if(u.followed){
            b.setText("Unfollow"); //followed (so show text unfollow)
        }
        else{
            b.setText("Follow");
        }
    }

    /*  //MEL SOLUTION
    //When the left button is clicked,
    // it will toggle the text between Follow and Unfollow.
    // The variable followed is also updated to reflect this.

    //Modify the onClickListener of left button to show a Toast message.
    // The toast message will show Followed if the user click on the Follow button, and vice versa.
    public void onFollowClick (View v){
        u.followed = !u.followed; //switch between
        if(u.followed){
            Toast.makeText(this,"Followed",Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(this,"Unfollowed",Toast.LENGTH_SHORT).show();
        }
        setFollowBtn();
    }

     */ //MEL SOLUTION!!

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("Debug", "start");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("Debug", "stop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("Debug", "destroy");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("Debug", "pause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("Debug", "resume");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("Debug", "restart");
    }
}