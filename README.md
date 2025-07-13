# CP317 Student Mark Tracker

Reads student names and course grades from two input files, calculates final grades using weighted averages, and outputs the results sorted by student ID.

## Authors
- Michael Tahirovic
- Ben Hamilton
- Carter Chan
- Jason

## Project Structure

- `src/`: Java source code (`Enrollment.java`, `GradeProcessor.java`)
- `input/`: Sample input files
- `output/`: Example output file
- `QATestInput/`: QA testing input files for various scenarios
- `docs/`: Design documents, planning notes, and requirement PDFs

## Key Features

- Reads and merges two `.txt` input files
- Calculates final grades using:
  - Test 1, 2, 3 → 20% each
  - Final exam → 40%
- Outputs a sorted file by Student ID
- Implements OOP principles:
  - Encapsulation
  - Polymorphism
- Applies offensive programming with error logging

## Application Link (Extra)

https://grade-scribe-sorted-results.lovable.app/

## QA Testing

This section outlines how to conduct manual, integration, and error handling tests for the Student Mark Tracker project. Use the table below to document your test results for each scenario.

### Classes and Offensive Programming Principles Tested

**Classes:**
- ✅ `Main` - Entry point and orchestration
- ✅ `FileProcessor` - File I/O and data validation
- ✅ `Student` - Student data model and grade management
- ✅ `Grade` - Abstract base class for grades
- ✅ `TripleGrade` - Concrete grade implementation with calculation

**Offensive Programming Principles Tested:**
- ✅ **Input Validation**: Student ID format (9 digits), course code format (2 letters + 3 digits)
- ✅ **Data Type Validation**: Numeric grade parsing with NumberFormatException handling
- ✅ **Null Checking**: TripleGrade.calculateFinalGrade() checks for null grades
- ✅ **Boundary Conditions**: Empty files, missing fields, invalid formats
- ✅ **Error Logging**: Comprehensive System.err.println() for all error cases
- ✅ **Graceful Degradation**: Program continues processing valid records when invalid ones are encountered
- ✅ **File I/O Error Handling**: Try-catch blocks for IOException

### Test Documentation Template

| Test Case         | Input Change / Scenario                | Expected Result                        | Actual Result                          | Pass/Fail |
|-------------------|----------------------------------------|----------------------------------------|----------------------------------------|-----------|
| Valid Data        | None                                   | All students/courses processed         | As expected                            | Pass      |
| Invalid ID        | ID = 12345                             | Error logged, line skipped             | Error logged, line skipped             | Pass      |
| Bad Course Code   | Code = C317                            | Error logged, line skipped             | Error logged, line skipped             | Pass      |
| Non-numeric Grade | Grade = "ninety"                      | Error logged, line skipped             | Error logged, line skipped             | Pass      |
| Empty Name        | Name = (empty)                         | Error logged, line skipped             |                                        |           |
| Missing Fields    | Fewer than 6 fields in course file     | Error logged, line skipped             |                                        |           |
| Student Not Found | Course file ID not in name file        | Error logged, line skipped             |                                        |           |
| Null Grades       | Grade = null                           | Exception thrown                       |                                        |           |
| Empty Files       | Empty input files                      | Error logged, no output                |                                        |           |
| Negative Grades   | Grade = -10                            | Processed (no validation)              |                                        |           |
| Overflow Grades   | Grade = 999999                         | Processed (no validation)              |                                        |           |
| Decimal Grades    | Grade = 80.5                           | Processed correctly                    |                                        |           |
| ...               | ...                                    | ...                                    | ...                                    | ...       |

Fill in the table as you conduct each test. For each scenario, copy the relevant test files from `QATestInput/` to `input/` directory as described, run the program, and record the results.

### How to Use QA Test Files

1. **Copy test files to input directory:**
   ```bash
   cp QATestInput/NameFile_invalid_id.txt input/NameFile.txt
   cp QATestInput/CourseFile_valid.txt input/CourseFile.txt
   ```

2. **Run the program:**
   ```bash
   javac src/*.java
   java -cp src Main
   ```

3. **Observe console output** for error messages and check the output file for results.

4. **Document results** in the test table above.

**Available Test Files in QATestInput/:**
- `NameFile_valid.txt` / `CourseFile_valid.txt` - Baseline valid data
- `NameFile_invalid_id.txt` - Student ID not 9 digits
- `NameFile_empty_name.txt` - Student with empty name
- `NameFile_empty_file.txt` - Empty name file
- `CourseFile_bad_course_code.txt` - Invalid course code format
- `CourseFile_non_numeric_grade.txt` - Non-numeric grade values
- `CourseFile_missing_fields.txt` - Missing data fields
- `CourseFile_student_not_found.txt` - Student ID not in name file
- `CourseFile_null_grades.txt` - Null grade values
- `CourseFile_empty_file.txt` - Empty course file
- `CourseFile_negative_grades.txt` - Negative grade values
- `CourseFile_overflow_grades.txt` - Very large grade values
- `CourseFile_decimal_grades.txt` - Decimal grade values
