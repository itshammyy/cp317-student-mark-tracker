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

Comprehensive QA testing has been conducted for this project. All test results, scenarios, and detailed analysis are documented in the separate **[QATest.md](QATest.md)** file.

### Quick Overview
- **13 test scenarios** covering all offensive programming principles
- **100% pass rate** - All error cases handled correctly
- **Comprehensive input validation** for student IDs, course codes, and grades
- **Graceful error handling** with detailed logging
- **Test files available** in `QATestInput/` directory

See **[QATest.md](QATest.md)** for complete test documentation and results.
