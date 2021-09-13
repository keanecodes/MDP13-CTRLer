package sg.edu.ntu.scse.mdp13;

import android.os.Bundle;

import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import sg.edu.ntu.scse.mdp13.map.GridMapController;
import sg.edu.ntu.scse.mdp13.map.GridMap;

public class MainActivity extends AppCompatActivity {

    private GridMapController gridMapController;
    private GridMap finder = new GridMap();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activty_main);

        gridMapController = findViewById(R.id.pathGrid);
        finder = gridMapController.getFinder();

        Button btnReset = (Button)this.findViewById(R.id.btn_reset);

        btnReset.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                gridMapController.setSolving(false);
                finder.resetGrid();
                gridMapController.invalidate();
            }
        });
    }

    private void makeToast(boolean found) {
        if (found) {
            Toast toast = Toast.makeText(this, "Path Found.", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.BOTTOM, 0, -200);
            toast.show();
        } else {
            Toast toast = Toast.makeText(this, "No Path Found.", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.BOTTOM, 0, -200);
            toast.show();
        }
    }
}