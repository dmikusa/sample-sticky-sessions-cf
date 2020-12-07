<#import "/spring.ftl" as spring />
<html>
<head>
    <title>Session Test</title>
</head>
<body>
<h2>Session [${id}]</h2>
<p>Current count is ${count}</p>
<p>Current IP is ${(ip)!"(not found)"}</p>
<p>Current Instance is ${(inst)!"(not found)"}</p>
<p>Current Guid is ${(guid)!"(not found)"}</p>
<p><a href="<@spring.url '/reset' />">Reset Counter</a></p>
</body>
</html>
