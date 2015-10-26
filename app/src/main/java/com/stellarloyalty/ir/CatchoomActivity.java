package com.stellarloyalty.ir;

import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.craftar.CraftARActivity;
import com.craftar.CraftARCamera;
import com.craftar.CraftARCameraView;
import com.craftar.CraftARCloudRecognition;
import com.craftar.CraftARCloudRecognitionError;
import com.craftar.CraftARImage;
import com.craftar.CraftARImageHandler;
import com.craftar.CraftARItem;
import com.craftar.CraftARResponseHandler;
import com.craftar.CraftARSDK;

import java.util.ArrayList;

public class CatchoomActivity extends CraftARActivity implements CraftARResponseHandler, CraftARImageHandler, View.OnClickListener {

    final static String COLLECTION_TOKEN = "3f4e7c24532342a7";

    CraftARCamera mCraftARCamera;
    CraftARCloudRecognition mCloudRecognition;

    Button mScanButton;

    @Override
    public void onPostCreate() {
        View mainLayout = (View) getLayoutInflater().inflate(R.layout.activity_catchoom, null);
        CraftARCameraView cameraView = (CraftARCameraView) mainLayout.findViewById(R.id.camera_preview);
        super.setCameraView(cameraView);
        setContentView(mainLayout);

        // Initialize the SDK.
        CraftARSDK.init(getApplicationContext(), this);

        // Obtain the CraftARCloudRecognition module.
        mCloudRecognition = CraftARSDK.getCloudRecognition();
        // Indicate mCloudRecognition that this Activity is the handler that will receive the responses
        //  from the Cloud Image Recognition Service.
        mCloudRecognition.setResponseHandler(this);

        mCraftARCamera = (CraftARCamera) CraftARSDK.getCamera();
        mCraftARCamera.setImageHandler(this);

        mScanButton = (Button) findViewById(R.id.scan_button);
        mScanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCraftARCamera != null)
                    mCraftARCamera.takePicture();
            }
        });
    }

    @Override
    public void searchCompleted(ArrayList<CraftARItem> arrayList) {
        for (CraftARItem item : arrayList) {
            Toast.makeText(CatchoomActivity.this, item.getUrl(), Toast.LENGTH_LONG).show();
        }

        mCraftARCamera.restartCameraPreview();
    }

    @Override
    public void connectCompleted() {

    }

    @Override
    public void requestFailedResponse(int i, CraftARCloudRecognitionError craftARCloudRecognitionError) {

    }

    @Override
    public void requestImageReceived(CraftARImage craftARImage) {
        mCloudRecognition.searchWithImage(COLLECTION_TOKEN, craftARImage);
    }

    @Override
    public void requestImageError(String s) {

    }

    @Override
    public void onClick(View v) {

    }
}
