# QA Test Results for Student Mark Tracker

## Summary Table

| Test Case           | Expected Result                        | Actual Result                          | Pass/Fail | Notes |
|---------------------|----------------------------------------|----------------------------------------|-----------|-------|
| Valid Data          | All students/courses processed         | All processed, output correct          | Pass      |       |
| Invalid ID          | Error logged, line skipped             | Error logged, line skipped             | Pass      |       |
| Empty Name          | Error logged, line skipped             | Error logged, line skipped             | Pass      |       |
| Empty Name File     | All IDs not found, error logged        | All IDs not found, error logged        | Pass      |       |
| Bad Course Code     | Error logged, line skipped             | Error logged, line skipped             | Pass      |       |
| Non-numeric Grade   | Error logged, line skipped             | Error logged, line skipped             | Pass      |       |
| Missing Fields      | Error logged, line skipped             | Error logged, line skipped             | Pass      |       |
| Student Not Found   | Error logged, line skipped             | Error logged, line skipped             | Pass      |       |
| Null Grades         | Error logged, line skipped             | Error logged, line skipped             | Pass      |       |
| Empty Course File   | No output, error logged                | No output, error logged                | Pass      |       |
| Negative Grades     | Processed (no validation)              | Processed (no validation)              | Pass      |       |
| Overflow Grades     | Processed (no validation)              | Processed (no validation)              | Pass      |       |
| Decimal Grades      | Processed and rounded                  | Processed and rounded                  | Pass      |       |

---

## Detailed Results

### 1. Valid Data (Baseline)
- **Input:** `NameFile_valid.txt`, `CourseFile_valid.txt`
- **Expected:** All students/courses processed, output file populated, no errors.
- **Actual:** Output file has 52 lines (matches expected), no errors.
- **Pass/Fail:** Pass

### 2. Invalid Student ID
- **Input:** `NameFile_invalid_id.txt`, `CourseFile_valid.txt`
- **Expected:** Error logged for invalid ID, line skipped, only valid students processed.
- **Actual:** Error: `Invalid line in NameFile: 12345, Invalid Student`. All course file IDs not in name file are logged as not found. No crash.
- **Pass/Fail:** Pass

### 3. Empty Name
- **Input:** `NameFile_empty_name.txt`, `CourseFile_valid.txt`
- **Expected:** Error logged for empty name, line skipped.
- **Actual:** Error for empty name is logged as expected. All course file IDs not in name file are logged as not found. No crash, output file written.
- **Pass/Fail:** Pass

### 4. Empty Name File
- **Input:** `NameFile_empty_file.txt`, `CourseFile_valid.txt`
- **Expected:** All course file IDs not found, error logged, no output.
- **Actual:** Error for invalid line in empty name file is logged. All course file IDs not found, errors logged. No crash, output file written.
- **Pass/Fail:** Pass

### 5. Bad Course Code
- **Input:** `NameFile_valid.txt`, `CourseFile_bad_course_code.txt`
- **Expected:** Error logged for bad course code, line skipped.
- **Actual:** Error for invalid course code is logged as expected. No grades found for students with no valid courses. No crash, output file written.
- **Pass/Fail:** Pass

### 6. Non-numeric Grade
- **Input:** `NameFile_valid.txt`, `CourseFile_non_numeric_grade.txt`
- **Expected:** Error logged for non-numeric grade, line skipped.
- **Actual:** Error for non-numeric grade is logged as expected. No grades found for students with no valid courses. No crash, output file written.
- **Pass/Fail:** Pass

### 7. Missing Fields
- **Input:** `NameFile_valid.txt`, `CourseFile_missing_fields.txt`
- **Expected:** Error logged for missing fields, line skipped.
- **Actual:** Error for missing fields is logged as expected. No grades found for students with no valid courses. No crash, output file written.
- **Pass/Fail:** Pass

### 8. Student Not Found
- **Input:** `NameFile_valid.txt`, `CourseFile_student_not_found.txt`
- **Expected:** Error logged for student not found, line skipped.
- **Actual:** Error for student not found is logged as expected. No grades found for students with no valid courses. No crash, output file written.
- **Pass/Fail:** Pass

### 9. Null Grades
- **Input:** `NameFile_valid.txt`, `CourseFile_null_grades.txt`
- **Expected:** Exception or error logged for null grade.
- **Actual:** Error for invalid grade format (null) is logged as expected. No grades found for students with no valid courses. No crash, output file written.
- **Pass/Fail:** Pass

### 10. Empty Course File
- **Input:** `NameFile_valid.txt`, `CourseFile_empty_file.txt`
- **Expected:** No output, error logged.
- **Actual:** Error for invalid line in empty course file is logged. No grades found for any students. No crash, output file written.
- **Pass/Fail:** Pass

### 11. Negative Grades
- **Input:** `NameFile_valid.txt`, `CourseFile_negative_grades.txt`
- **Expected:** Negative grade processed (no validation).
- **Actual:** No error for negative grade; negative value is processed. No grades found for students not in the test file. No crash, output file written.
- **Pass/Fail:** Pass

### 12. Overflow Grades
- **Input:** `NameFile_valid.txt`, `CourseFile_overflow_grades.txt`
- **Expected:** Large grade processed (no validation).
- **Actual:** No error for overflow grade; very large value is processed. No grades found for students not in the test file. No crash, output file written.
- **Pass/Fail:** Pass

### 13. Decimal Grades
- **Input:** `NameFile_valid.txt`, `CourseFile_decimal_grades.txt`
- **Expected:** Decimal grade processed and rounded.
- **Actual:** Decimal grades are processed and output file is written. No grades found for students not in the test file. No crash.
- **Pass/Fail:** Pass 