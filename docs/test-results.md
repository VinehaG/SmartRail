# Test Results

| ID | Test Case | Expected Result | Actual Result | Status |
|---|---|---|---|---|
| TC-01 | Add valid passenger (ID: TEST01) | Passenger saved to CSV successfully | Passenger added without exceptions | PASS |
| TC-02 | Reject duplicate passenger ID | Throws `InvalidPassengerException` | Exception thrown and caught | PASS |
| TC-03 | Search existing passenger | Returns passenger object with correct name | Data matches exactly | PASS |
| TC-04 | Search default train (12001) | Returns Train object | Object fetched correctly | PASS |
| TC-05 | Successful Ticket Booking | Valid PNR generated, seat deducted | PNR received, payment processed | PASS |
| TC-06 | Search invalid PNR | Throws `BookingNotFoundException` | Exception thrown and caught | PASS |
| TC-07 | Cancel valid ticket | Status changes to CANCELLED, seat restored | Status updated and verified | PASS |
| TC-08 | Application Persistence | Data survives application restart | Data successfully loaded from CSV | PASS |

> [!NOTE]  
> All tests were run using the automated `tests/SmartRailTest.java` runner and successfully verified against the core requirements.
