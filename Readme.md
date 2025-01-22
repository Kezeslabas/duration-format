# Simple Duration Formatter App

A solution to the following task.

https://www.codewars.com/kata/human-readable-duration-format

For this solution Spring Boot was used, 
and the formatting can be tested in the following endpoint.

```
GET /durationformatter/format?seconds=1
```

In default, the app starts in the `8080` port,
and after importing the maven project, it should require no configuration.

> Note: The app counts `365` days as a full year, 
> not `365.2425` days.