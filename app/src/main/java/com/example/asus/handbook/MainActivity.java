package com.example.asus.handbook;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.File;
import java.io.IOException;

import ezvcard.Ezvcard;
import ezvcard.VCard;
import ezvcard.VCardVersion;
import ezvcard.parameter.EmailType;
import ezvcard.parameter.ImageType;
import ezvcard.parameter.TelephoneType;
import ezvcard.property.Photo;
import ezvcard.property.StructuredName;

public class MainActivity extends AppCompatActivity {
    public Photo photo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //VCard vcard = new VCard();
        try {
            VCard vcard = createVCard();
            String text = Ezvcard.write(vcard).version(VCardVersion.V3_0).go();
            Log.d("XXXXXXXX",text);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    private static VCard createVCard() throws IOException {
        VCard vcard = new VCard();
        StructuredName n = new StructuredName();
        n.setFamily("House");
        n.setGiven("Gregory");
        n.getPrefixes().add("Dr");
        n.getSuffixes().add("MD");
        vcard.setStructuredName(n);
        vcard.addEmail("johndoe@hotmail.com", EmailType.HOME);
        vcard.addTelephoneNumber("1-555-555-5678", TelephoneType.CELL);

        //File file = new File("image_test.jpg");
        File file = new File(Environment.getExternalStorageDirectory().toString() + "/Pictures/" + "image_test.jpg");
        Log.d("XXXXXXXX",file.getPath());
        Log.d("XXXXXXXX",Long.toString(file.length()));

       Photo  photo = new Photo(file, ImageType.JPEG);

        //vcard.addPhoto(photo);

        vcard.setFormattedName("Dr. Gregory House M.D.");


        return vcard;
    }
}
