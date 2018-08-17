package com.example.tejas.hack_iiid.History;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tejas.hack_iiid.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.ml.vision.FirebaseVision;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;
import com.google.firebase.ml.vision.document.FirebaseVisionDocumentText;
import com.google.firebase.ml.vision.document.FirebaseVisionDocumentTextRecognizer;

public class History extends AppCompatActivity {


    public static final int DOC_IMAGE_REQUEST = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        setUp();
    }

    private void setUp() {
        FirebaseApp.initializeApp(History.this);
        imageCapturer();
    }

    private void imageCapturer() {

        Button uploadImage = findViewById(R.id.upload_doc_pic);
        uploadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("bug", "clicked");
                //openCamera();
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, DOC_IMAGE_REQUEST);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == DOC_IMAGE_REQUEST) {
            if (data != null) {
                if (data.getExtras().get("data") != null) {
                    Bitmap docImageBitmap = (Bitmap) data.getExtras().get("data");
                    renderImageInView(docImageBitmap);
                    runModel(docImageBitmap);
                }
            }
        }
    }

    private void renderImageInView(Bitmap docImageBitmap) {
        findViewById(R.id.doc_pic_layout).setVisibility(View.VISIBLE);
        ImageView imageView = findViewById(R.id.doc_image_view);
        imageView.setImageBitmap(docImageBitmap);
    }

    private void runModel(Bitmap bitMap) {
        FirebaseVisionImage visionImage = FirebaseVisionImage.fromBitmap(bitMap);
        FirebaseVisionDocumentTextRecognizer textRecognizer = FirebaseVision.getInstance()
                .getCloudDocumentTextRecognizer();
        getResults(textRecognizer, visionImage);

    }

    private void getResults(FirebaseVisionDocumentTextRecognizer textRecognizer, FirebaseVisionImage visionImage) {
        final TextView ans = findViewById(R.id.doc_image_label);
        textRecognizer.processImage(visionImage)
                .addOnSuccessListener(new OnSuccessListener<FirebaseVisionDocumentText>() {
                    @Override
                    public void onSuccess(FirebaseVisionDocumentText result) {
                        // Task completed successfully
                        // ...
                        String textAns = "";
                        for (FirebaseVisionDocumentText.Block block : result.getBlocks()) {
                            textAns += block.getText() + "\n";
                        }
                        ans.setText(textAns);
                    }
                })
                .addOnFailureListener(
                        new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                ans.setText(e.getMessage());
                            }
                        });
    }


}
