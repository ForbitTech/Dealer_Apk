package com.forbitbd.Dealer.ui.Profile;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.forbitbd.Dealer.R;
import com.forbitbd.Dealer.utils.AppPreference;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.squareup.picasso.Picasso;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener, ProfileContract.View{

    private ProfileContract.Presenter mPresenter;

    private TextInputEditText name,email, org, phone, address;
    private CircularImageView image;
    private MaterialButton btnupdate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        mPresenter = new ProfilePresenter(this);

        name = findViewById(R.id.username);
        email = findViewById(R.id.useremail);
        org = findViewById(R.id.org_name);
        phone = findViewById(R.id.userphone);
        address = findViewById(R.id.useraddress);
        image = findViewById(R.id.userphoto);
        btnupdate = findViewById(R.id.update);
        btnupdate.setOnClickListener(this);




        name.setText(AppPreference.getInstance(this).getDealer().getName());
        email.setText(AppPreference.getInstance(this).getDealer().getEmail());
        phone.setText(AppPreference.getInstance(this).getDealer().getMobile());
        address.setText(AppPreference.getInstance(this).getDealer().getAddress());
        org.setText(AppPreference.getInstance(this).getDealer().getOrganization_name());

        Picasso.with(this).load(AppPreference.getInstance(this).getDealer().getImage()).into(image);
    }

    @Override
    public void onClick(View view) {

        String etname = name.getText().toString().trim();
        String etphone = phone.getText().toString().trim();
        String etaddress = address.getText().toString().trim();
        String etorg = org.getText().toString().trim();
        mPresenter.updateProfile(etname,etaddress,etorg,etphone);


    }
}