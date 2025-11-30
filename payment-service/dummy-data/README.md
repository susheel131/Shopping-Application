# Dummy Data for Payment Service

## Overview
This directory contains test and dummy data files for testing the Payment Service API.

## Files

### 1. init-dummy-data.sql
SQL script containing initial test data for the database.

**Contents:**
- 5 Test Users (John Doe, Jane Smith, Robert Wilson, Emily Brown, Michael Johnson)
- 6 Payment Methods (Credit Cards and Debit Cards)
- 10 Test Payments (various statuses: COMPLETED, PENDING, FAILED)
- 3 Test Refunds (different refund reasons)

**Usage:**
```bash
mysql -u root -p payment_service < init-dummy-data.sql
```

### 2. sample-api-requests.json
JSON file with sample API requests and expected responses.

**Contents:**
- Sample payment processing request
- Sample payment method addition request
- Sample payment history query
- Sample refund initiation request
- Test user list

**How to use:**
- Import into Postman or any REST client
- Use as reference for API testing
- Copy-paste request bodies for manual testing

## Test Data Details

### Users
| ID | Name | Email |
|---|---|---|
| 1 | John Doe | john@example.com |
| 2 | Jane Smith | jane@example.com |
| 3 | Robert Wilson | robert@example.com |
| 4 | Emily Brown | emily@example.com |
| 5 | Michael Johnson | michael@example.com |

### Payment Status Distribution
- COMPLETED: 8 payments
- PENDING: 1 payment
- FAILED: 1 payment

### Payment Methods
- Credit Cards: 4
- Debit Cards: 2

## How to Use

### Database Setup
1. Create a new MySQL database
2. Run the init-dummy-data.sql script
3. Verify data was inserted correctly

### API Testing
1. Start the Payment Service application
2. Open sample-api-requests.json
3. Use the requests to test API endpoints
4. Modify userId, methodId, etc. as needed

## Sample API Endpoints

- POST /api/v1/payments/process - Test with user 1-5
- GET /api/v1/payments/history/1 - View payment history
- POST /api/v1/payments/methods - Add new payment method
- POST /api/v1/payments/{paymentId}/refund - Process refunds

## Notes

- All card numbers are dummy/test numbers (PCI compliant)
- Transaction IDs are formatted as TXN-XXXXX
- All amounts are in USD
- Dates can be modified in SQL script as needed
- Add more test data by extending the INSERT statements

## Integration with Testing Tools

### Postman
1. Import sample-api-requests.json
2. Set base URL to http://localhost:8080
3. Add Authorization header with Bearer token
4. Execute requests

### JUnit/Integration Tests
- Load SQL data before running tests
- Use userIds 1-5 for test cases
- Verify against expected refund amounts

## Maintenance

To add more test data:
1. Edit init-dummy-data.sql
2. Add new INSERT statements
3. Commit and push changes
4. Re-run setup process

## Support

For issues with dummy data, refer to API_DOCUMENTATION.md and API_IMPLEMENTATION_GUIDE.md
