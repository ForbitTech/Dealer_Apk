package com.forbitbd.Dealer.ui.Device_Entry;

public interface DeviceEntry_Contract {

    interface Presenter{

        void device_entry(String _id,String etvehicle,String etreg_number, String etdevice_id,String etdevice_number, String etcustomer_number, String etcustomer_email , String etcustomer_name);

    }

    interface View{

    }
}
