package sg.edu.ntu.scse.mdp13.bluetooth;

import android.bluetooth.BluetoothDevice;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import sg.edu.ntu.scse.mdp13.R;

public class BluetoothDataAdapter extends RecyclerView.Adapter<BluetoothDataAdapter.BluetoothViewHolder> {
    private ArrayList<BluetoothDevice> devices;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    static class BluetoothViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        View view;
        BluetoothViewHolder(View v) {
            super(v);
            view = v;
        }
    }

    public BluetoothDataAdapter(ArrayList<BluetoothDevice> devices) {
        this.devices = devices;
    }

    // Create new views (invoked by the layout manager)
    @Override
    @NonNull
    public BluetoothViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // create a new view
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_available_bluetooth_devices,
                parent, false);
        return new BluetoothViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(@NonNull BluetoothViewHolder holder, int position) {
        String device_name = this.devices.get(position).getName();
        String address = this.devices.get(position).getAddress();
        TextView textview = holder.view.findViewById(R.id.bluetooth_device_text);
        Button button = holder.view.findViewById(R.id.bluetooth_connect_btn);
        button.setTag(this.devices.get(position));
        if (device_name != null) {
            textview.setText(String.format("%s (%s)", address, device_name));
        } else {
            textview.setText(String.format("%s", address));
        }
    }

    @Override
    public int getItemCount() {
        return devices.size();
    }

    public void add(BluetoothDevice device) {
        this.devices.add(device);
        this.notifyDataSetChanged();
    }

    public void remove(BluetoothDevice device) {
        this.devices.remove(device);
        this.notifyDataSetChanged();
    }

    public void clear() {
        this.devices.clear();
        this.notifyDataSetChanged();
    }
}