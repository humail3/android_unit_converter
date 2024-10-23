package com.gopi.unitconverterapp;


import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Spinner sourceUnitSpinner, destinationUnitSpinner;
    private EditText valueEditText;
    private Button convertButton;
    private TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sourceUnitSpinner = findViewById(R.id.sourceUnitSpinner);
        destinationUnitSpinner = findViewById(R.id.destinationUnitSpinner);
        valueEditText = findViewById(R.id.valueEditText);
        convertButton = findViewById(R.id.convertButton);
        resultTextView = findViewById(R.id.resultTextView);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.unit_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sourceUnitSpinner.setAdapter(adapter);
        destinationUnitSpinner.setAdapter(adapter);

        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sourceUnit = sourceUnitSpinner.getSelectedItem().toString();
                String destinationUnit = destinationUnitSpinner.getSelectedItem().toString();
                String valueStr = valueEditText.getText().toString();

                if (valueStr.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please enter a value", Toast.LENGTH_SHORT).show();
                    return;
                }

//                display the converted value with only two digits after the decimal point
                double convertedValue = convertUnits(sourceUnit, destinationUnit, valueStr);
                resultTextView.setText(String.format("%.2f %s", convertedValue, destinationUnit));

            }
        });
    }


    //    function that takes the source unit, destination unit, and input value as parameters, and returns the converted value
    private double convertUnits(String sourceUnit, String destinationUnit, String valueStr) {


        try {
            double value = Double.parseDouble(valueStr);


            // Length Conversions
            if (sourceUnit.equals("Inches")) {
                if (destinationUnit.equals("Centimeters")) {
                    return value * 2.54;
                } else if (destinationUnit.equals("Feet")) {
                    return value / 12;
                } else if (destinationUnit.equals("Yards")) {
                    return value / 36;
                } else if (destinationUnit.equals("Miles")) {
                    return value / 63360;
                }
            } else if (sourceUnit.equals("Feet")) {
                if (destinationUnit.equals("Inches")) {
                    return value * 12;
                } else if (destinationUnit.equals("Centimeters")) {
                    return value * 30.48;
                } else if (destinationUnit.equals("Yards")) {
                    return value / 3;
                } else if (destinationUnit.equals("Miles")) {
                    return value / 5280;
                }
            } else if (sourceUnit.equals("Yards")) {
                if (destinationUnit.equals("Inches")) {
                    return value * 36;
                } else if (destinationUnit.equals("Feet")) {
                    return value * 3;
                } else if (destinationUnit.equals("Centimeters")) {
                    return value * 91.44;
                } else if (destinationUnit.equals("Miles")) {
                    return value / 1760;
                }
            } else if (sourceUnit.equals("Miles")) {
                if (destinationUnit.equals("Inches")) {
                    return value * 63360;
                } else if (destinationUnit.equals("Feet")) {
                    return value * 5280;
                } else if (destinationUnit.equals("Yards")) {
                    return value * 1760;
                } else if (destinationUnit.equals("Centimeters")) {
                    return value * 160934.4;
                }
            }


            // Weight Conversions
            else if (sourceUnit.equals("Pounds")) {
                if (destinationUnit.equals("Kilograms")) {
                    return value * 0.453592;
                } else if (destinationUnit.equals("Ounces")) {
                    return value * 16;
                } else if (destinationUnit.equals("Tons")) {
                    return value / 2000;
                }
            } else if (sourceUnit.equals("Ounces")) {
                if (destinationUnit.equals("Pounds")) {
                    return value / 16;
                } else if (destinationUnit.equals("Kilograms")) {
                    return value / 35.274;
                } else if (destinationUnit.equals("Tons")) {
                    return value / 35274;
                }
            } else if (sourceUnit.equals("Tons")) {
                if (destinationUnit.equals("Pounds")) {
                    return value * 2000;
                } else if (destinationUnit.equals("Ounces")) {
                    return value * 35274;
                } else if (destinationUnit.equals("Kilograms")) {
                    return value * 907.185;
                }
            }


            // Temperature Conversions
            else if (sourceUnit.equals("Celsius")) {
                if (destinationUnit.equals("Fahrenheit")) {
                    return (value * 1.8) + 32;
                } else if (destinationUnit.equals("Kelvin")) {
                    return value + 273.15;
                }
            } else if (sourceUnit.equals("Fahrenheit")) {
                if (destinationUnit.equals("Celsius")) {
                    return (value - 32) / 1.8;
                } else if (destinationUnit.equals("Kelvin")) {
                    return (value + 459.67) * 5/9;
                }
            } else if (sourceUnit.equals("Kelvin")) {
                if (destinationUnit.equals("Celsius")) {
                    return value - 273.15;
                } else if (destinationUnit.equals("Fahrenheit")) {
                    return (value * 9/5) - 459.67;
                }
            }


            // Handle cases where source unit and destination unit are the same
            if (sourceUnit.equals(destinationUnit)) {
                return value;
            }


        } catch (NumberFormatException e) {
            Toast.makeText(this, "Invalid input value", Toast.LENGTH_SHORT).show();
        }

        // If conversion is not implemented, return 0
        return 0;

    }


}
