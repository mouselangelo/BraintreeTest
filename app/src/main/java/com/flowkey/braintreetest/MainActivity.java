package com.flowkey.braintreetest;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.braintreepayments.api.dropin.DropInActivity;
import com.braintreepayments.api.dropin.DropInRequest;
import com.braintreepayments.api.dropin.DropInResult;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    // Replace this with the braintree client token generated by the server.
    // Note: Currently this is a SANDBOX token
    private String braintreeClientToken = "eyJ2ZXJzaW9uIjoyLCJhdXRob3JpemF0aW9uRmluZ2VycHJpbnQiOiJleUowZVhBaU9pSktWMVFpTENKaGJHY2lPaUpGVXpJMU5pSXNJbXRwWkNJNklqSXdNVGd3TkRJMk1UWXRjMkZ1WkdKdmVDSXNJbWx6Y3lJNklrRjFkR2g1SW4wLmV5SmxlSEFpT2pFMU9ERXdOek0xTkRrc0ltcDBhU0k2SW1FMU1UVm1OR0ZrTFdJMU1USXROR1UzTnkwNE56TXlMVFZpTkRCaFpUUmlORFZtT0NJc0luTjFZaUk2SW5NNU16TTBhalZtY1hrM2MzSXpOSGtpTENKcGMzTWlPaUpCZFhSb2VTSXNJbTFsY21Ob1lXNTBJanA3SW5CMVlteHBZMTlwWkNJNkluTTVNek0wYWpWbWNYazNjM0l6TkhraUxDSjJaWEpwWm5sZlkyRnlaRjlpZVY5a1pXWmhkV3gwSWpwbVlXeHpaWDBzSW5KcFoyaDBjeUk2V3lKdFlXNWhaMlZmZG1GMWJIUWlYU3dpYjNCMGFXOXVjeUk2ZXlKdFpYSmphR0Z1ZEY5aFkyTnZkVzUwWDJsa0lqb2labXh2ZDJ0bGVVVlZVaUo5ZlEueS05WXJFT1p0UkRrOGR4ZVVtMnM3cjV0RzFrVjdWaWRiZlg4WnVDemFrUUJIMXNlUnZXNUpLVTJFUkwtOWN0bDNQMTdXZWcxb3NsM1laS0dBSGJIQlEiLCJjb25maWdVcmwiOiJodHRwczovL2FwaS5zYW5kYm94LmJyYWludHJlZWdhdGV3YXkuY29tOjQ0My9tZXJjaGFudHMvczkzMzRqNWZxeTdzcjM0eS9jbGllbnRfYXBpL3YxL2NvbmZpZ3VyYXRpb24iLCJncmFwaFFMIjp7InVybCI6Imh0dHBzOi8vcGF5bWVudHMuc2FuZGJveC5icmFpbnRyZWUtYXBpLmNvbS9ncmFwaHFsIiwiZGF0ZSI6IjIwMTgtMDUtMDgifSwiY2hhbGxlbmdlcyI6WyJjdnYiXSwiZW52aXJvbm1lbnQiOiJzYW5kYm94IiwiY2xpZW50QXBpVXJsIjoiaHR0cHM6Ly9hcGkuc2FuZGJveC5icmFpbnRyZWVnYXRld2F5LmNvbTo0NDMvbWVyY2hhbnRzL3M5MzM0ajVmcXk3c3IzNHkvY2xpZW50X2FwaSIsImFzc2V0c1VybCI6Imh0dHBzOi8vYXNzZXRzLmJyYWludHJlZWdhdGV3YXkuY29tIiwiYXV0aFVybCI6Imh0dHBzOi8vYXV0aC52ZW5tby5zYW5kYm94LmJyYWludHJlZWdhdGV3YXkuY29tIiwiYW5hbHl0aWNzIjp7InVybCI6Imh0dHBzOi8vb3JpZ2luLWFuYWx5dGljcy1zYW5kLnNhbmRib3guYnJhaW50cmVlLWFwaS5jb20vczkzMzRqNWZxeTdzcjM0eSJ9LCJ0aHJlZURTZWN1cmVFbmFibGVkIjp0cnVlLCJwYXlwYWxFbmFibGVkIjp0cnVlLCJwYXlwYWwiOnsiZGlzcGxheU5hbWUiOiJmbG93a2V5IiwiY2xpZW50SWQiOiJBUjJ4NXpScFhZRTVkd1BCRlZlZEhrRVctUGFQY2wxaGgtRGdoekJjdzRKZjdGckdINnM3T1locURxYTlQMEJhVTgtdXYtTmR1TC1rbkJheSIsInByaXZhY3lVcmwiOiJodHRwOi8vZXhhbXBsZS5jb20vcHAiLCJ1c2VyQWdyZWVtZW50VXJsIjoiaHR0cDovL2V4YW1wbGUuY29tL3RvcyIsImJhc2VVcmwiOiJodHRwczovL2Fzc2V0cy5icmFpbnRyZWVnYXRld2F5LmNvbSIsImFzc2V0c1VybCI6Imh0dHBzOi8vY2hlY2tvdXQucGF5cGFsLmNvbSIsImRpcmVjdEJhc2VVcmwiOm51bGwsImFsbG93SHR0cCI6dHJ1ZSwiZW52aXJvbm1lbnROb05ldHdvcmsiOmZhbHNlLCJlbnZpcm9ubWVudCI6Im9mZmxpbmUiLCJ1bnZldHRlZE1lcmNoYW50IjpmYWxzZSwiYnJhaW50cmVlQ2xpZW50SWQiOiJtYXN0ZXJjbGllbnQzIiwiYmlsbGluZ0FncmVlbWVudHNFbmFibGVkIjp0cnVlLCJtZXJjaGFudEFjY291bnRJZCI6ImZsb3drZXlFVVIiLCJjdXJyZW5jeUlzb0NvZGUiOiJFVVIifSwibWVyY2hhbnRJZCI6InM5MzM0ajVmcXk3c3IzNHkiLCJ2ZW5tbyI6Im9mZiIsIm1lcmNoYW50QWNjb3VudElkIjoiZmxvd2tleUVVUiJ9";

    static int DROP_IN_REQUEST_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void showBraintreeDropInUI(View view) {
        DropInRequest request = new DropInRequest().clientToken(braintreeClientToken);
        startActivityForResult(request.getIntent(this), DROP_IN_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode != DROP_IN_REQUEST_CODE) {
            super.onActivityResult(requestCode, resultCode, data);
        } else {
            if (resultCode == AppCompatActivity.RESULT_CANCELED) {
                // the user canceled
                System.out.println("Cancelled");

                Toast.makeText(this, "Cancelled", Toast.LENGTH_SHORT).show();

            } else if (resultCode == AppCompatActivity.RESULT_OK) {
                DropInResult result = data.getParcelableExtra(DropInResult.EXTRA_DROP_IN_RESULT);
                // use the result to update your UI and send the payment method nonce to your server
                System.out.println(result);

                new AlertDialog.Builder(this)
                        .setTitle("Success")
                        .setMessage(result.getPaymentMethodType().getCanonicalName())
                        .create()
                        .show();
            } else {
                // handle errors here, an exception may be available in
                Exception error = (Exception) data.getSerializableExtra(DropInActivity.EXTRA_ERROR);
                System.out.println(error);

                new AlertDialog.Builder(this)
                        .setTitle("Error")
                        .setMessage(error.getMessage())
                        .create()
                        .show();
            }
        }
    }
}
