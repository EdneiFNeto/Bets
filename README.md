# Simulate Animal

## Create bets

```json
{
  "player": {
    "name": "Jose",
    "cpf": "000.000.000-00"
  },
  "bets": [
    {
      "type": "Milhar",
      "numbers": [
        "0001",
        "0003",
        "0003"
      ],
      "award": {
        "one": true,
        "two": false,
        "three": false,
        "four": false,
        "five": false,
        "oneToFive": false
      },
      "lottery": [
        {
          "name": "Pix 01",
          "hour": "12:00"
        },{
          "name": "Pix 04",
          "hour": "14:00"
        }
      ]
    }
  ]
}
```

## Result

```json
{
  "one": "1201",
  "two": "2003",
  "three": "0018",
  "four": "1001",
  "five": "0011",
  "date": "12:00",
  "lotteryId": {
    "name": "Pix 04",
    "hour": "14:00"
  }
}
```