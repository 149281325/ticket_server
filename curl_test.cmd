
REM GET
curl -H "Accept: application/json" -X GET "http://localhost:7001/TicketServer/webresources/org.ticket.entity.tmcardtype"

REM PUT
curl -H "Content-Type: application/json" -X PUT "http://localhost:7001/TicketServer/webresources/card" -d {"""type""":"""add""","""uid""":"""FFF9FFFF""","""count""":10}

curl -H "Content-Type: application/json" -X PUT "http://localhost:7001/TicketServer/webresources/card" -d {"""type""":"""add""","""uid""":"""FFFFFFFE""","""balance""":500.00}

curl -H "Content-Type: application/json" -X PUT "http://localhost:7001/TicketServer/webresources/card" -d {"""type""":"""add""","""uid""":"""FFFFFFF0""","""start_time""":"""20150801""","""end_time""":"""20150802""","""count""":10}

curl -H "Content-Type: application/json" -X PUT "http://localhost:7001/TicketServer/webresources/card" -d {"""type""":"""add""","""uid""":"""0FFFFFFF""","""start_time""":"""20150801""","""end_time""":"""20150802""","""balance""":200.00}

curl -H "Content-Type: application/json" -X PUT "http://localhost:7001/TicketServer/webresources/card" -d {"""type""":"""add""","""uid""":"""00000001""","""start_time""":"""20150801""","""end_time""":"""20150802""","""balance""":200.00,"""single_day""":"""true"""}

curl -H "Content-Type: application/json" -X PUT "http://localhost:7001/TicketServer/webresources/card" -d {"""type""":"""add""","""uid""":"""00000888""","""staff""":"""true"""}

REM POST
curl -H "Content-Type: application/json" -X POST "http://localhost:7001/TicketServer/webresources/card" -d {"""type""":"""check""","""uid""":"""FFF9FFFF""","""count""":1}

curl -H "Content-Type: application/json" -X POST "http://localhost:7001/TicketServer/webresources/card" -d {"""type""":"""check""","""uid""":"""00000888"""}

REM nagitive: may not in date
curl -H "Content-Type: application/json" -X POST "http://localhost:7001/TicketServer/webresources/card" -d {"""type""":"""check""","""uid""":"""FFFFFFF0""","""count""":1,"""valid_check""":"""true"""}

curl -H "Content-Type: application/json" -X POST "http://localhost:7001/TicketServer/webresources/card" -d {"""type""":"""check""","""uid""":"""FFFFFFFE""","""balance""":501.00}

REM nagitive: out of balance
curl -H "Content-Type: application/json" -X POST "http://localhost:7001/TicketServer/webresources/card" -d {"""type""":"""check""","""uid""":"""0FFFFFFF""","""balance""":1.00,"""valid_check""":"""true"""}
