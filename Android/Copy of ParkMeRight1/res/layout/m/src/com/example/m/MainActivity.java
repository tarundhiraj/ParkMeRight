
    
    package com.example.m;  
    import java.util.ArrayList;
    import java.util.List;
    import android.app.Activity;
    import android.os.Bundle;
    import android.view.View;
    import android.view.View.OnClickListener;
    import android.widget.ArrayAdapter;
    import android.widget.Button;
    import android.widget.Spinner;
    import android.widget.Toast;
    public class MainActivity extends Activity {
    private Spinner spinner1, spinner2;
    private Button button;
   @Override

      public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

     
           addItemsOnSpinner2();
           addListenerOnButton();
   
        addListenerOnSpinnerItemSelection();
         }
        
         // add items into spinner dynamically
   
      public void addItemsOnSpinner2() {
          spinner2 = (Spinner) findViewById(R.id.spinner2);
           List list = new ArrayList();
           list.add("Select Slot");
           list.add("Slot 1");
           list.add("Slot 2");
           list.add("Slot 3");
           list.add("Slot 4");
          ArrayAdapter dataAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, list);
   
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
          spinner2.setAdapter(dataAdapter);
        }
        
        public void addListenerOnSpinnerItemSelection() {
           spinner1 = (Spinner) findViewById(R.id.spinner1);
          spinner1.setOnItemSelectedListener(new MyOnItemSelectedListener());
   
      }
         public void addListenerOnButton() {
           spinner1 = (Spinner) findViewById(R.id.spinner1);
           spinner2 = (Spinner) findViewById(R.id.spinner2);
           button = (Button) findViewById(R.id.button);
   
        button.setOnClickListener(new OnClickListener() {
        
             @Override
             public void onClick(View v) {
        
               Toast.makeText(MainActivity.this,
               "Result : " +
   
                    "\nSpinner 1 : "+ String.valueOf(spinner1.getSelectedItem()) +
                      "\nSpinner 2 : "+ String.valueOf(spinner2.getSelectedItem()),
                   Toast.LENGTH_SHORT).show();
       }
        
           });
         }
       }


