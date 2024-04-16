package com.edwinsoto.tipoftheday.tip;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TipControllerTest {

    @Test
    void getTipOfTheDay() {
    }

    @Test
    void saveTip() {
//        Example return value...
//        {"id":"661dfac4910b7016fe1ff2c1","question":"**\n\nHow can I efficiently handle missing values in Python 3 data structures?\n\n**","answer":"**\n\nHandling missing values in Python 3 data structures is crucial, as missing data can distort analysis and lead to errors. Here are some efficient techniques:\n\n**1. Using `None`:**\n\n- `None` is the standard missing value indicator in Python.\n- Use it to explicitly represent missing data in lists, dictionaries, and other data structures.\n- Be aware that `None` is not the same as an empty string or integer.\n\n**2. Checking for Missing Values:**\n\n- Use the `in` operator to check if a missing value is present in a data structure.\n- Use the `is None` operator to check for `None` specifically.\n- For dataframes, use the `isnull()` method to identify missing values.\n\n**3. Handling Missing Values during Data Analysis:**\n\n- Use conditional statements to handle missing values during data analysis.\n- Use imputation techniques to fill in missing values with appropriate values.\n- Use data cleaning libraries like `pandas` to efficiently handle missing values.\n\n**4. Using Data Validation Functions:**\n\n- Use data validation functions to enforce data quality and prevent missing values from entering data structures.\n- Use `assert` statements to check for missing values and raise an error if found.\n\n**5. Handling Missing Values in Loops:**\n\n- Use conditional statements to skip missing values in loops.\n- Use list comprehension to efficiently filter out missing values.\n- Use the `try`/`except` block to handle missing values gracefully.\n\n**Example:**\n\n```python\n# Create a list with missing values\ndata = [1, None, 3, None, 5]\n\n# Check for missing values\nfor value in data:\n    if value is None:\n        print(\"Missing value found\")\n\n# Handle missing values during analysis\nfiltered_data = [value for value in data if value is not None]\naverage = sum(filtered_data) / len(filtered_data)\n\n# Use pandas to handle missing values in a dataframe\nimport pandas as pd\n\ndf = pd.DataFrame({\"col1\": [1, None, 3], \"col2\": [None, 2, 4]})\ndf = df.dropna()  # Drop rows with missing values\n```","date":"04-16-2024","endpoint":"python-tip"}
    }
}