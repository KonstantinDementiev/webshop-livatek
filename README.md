# Webshop Livatek
Test task for Livatek

--------------------------------------------------------

Before run App it is necessary to create DB table with name `webshop` in PostgreSQL,
with user/pass: postgres/postgres.

To run App jar file use command with next format:

`java -jar <absolute filepath to JAR file> <amount> <price> <type> [<custom> ...]`

Where:

• `<amount>` is the amount of products

• `<price>` is the product cost in DKK

• `<type>` is a product identifier to determine if freight should be added. For testing purposes
this can only be online or book and all calculations of type book needs freight to be added to
the price.

• `[<custom> ...]` Zero or more parameters of the format `<name>=<value>`
  For the test we only assume `--vat=<country code>`, `--input-currency=<currency code>`
  and/or `--output-currency=<currency code>` can be supplied.