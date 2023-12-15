package com.kv.pribizz.model

data class ResultChartModel(
    val markets: ArrayList<Market>,
    val result: LinkedHashMap<String, LinkedHashMap<String, Result>>,
)

data class Market(val id: String, val name: String)

data class Result(
    val id: String,
    val market_id: String,
    val market_result_andar: String,
    val market_result_bahar: String
)

data class ResultUI(val value: String, val is_header: Boolean = false)
/*
{
    "code": 0,
    "error": false,
    "message": "Result",
    "data": {
        "markets": [
            {
                "id": "1",
                "name": "GALI"
            },
            {
                "id": "2",
                "name": "Disawar"
            }
        ],
        "result": {
            "2022-05-1": {
                "GALI": {},
                "Disawar": {}
            },
            "2022-05-2": {
                "GALI": {},
                "Disawar": {}
            },
            "2022-05-3": {
                "GALI": {},
                "Disawar": {}
            },
            "2022-05-4": {
                "GALI": {},
                "Disawar": {
                    "id": "1",
                    "market_id": "2",
                    "market_result_andar": "1",
                    "market_result_bahar": "0"
                }
            },
            "2022-05-5": {
                "GALI": {},
                "Disawar": {}
            },
            "2022-05-6": {
                "GALI": {},
                "Disawar": {}
            },
            "2022-05-7": {
                "GALI": {},
                "Disawar": {}
            },
            "2022-05-8": {
                "GALI": {},
                "Disawar": {}
            },
            "2022-05-9": {
                "GALI": {},
                "Disawar": {}
            },
            "2022-05-10": {
                "GALI": {},
                "Disawar": {}
            },
            "2022-05-11": {
                "GALI": {},
                "Disawar": {}
            },
            "2022-05-12": {
                "GALI": {},
                "Disawar": {}
            },
            "2022-05-13": {
                "GALI": {},
                "Disawar": {}
            },
            "2022-05-14": {
                "GALI": {},
                "Disawar": {}
            },
            "2022-05-15": {
                "GALI": {},
                "Disawar": {}
            },
            "2022-05-16": {
                "GALI": {},
                "Disawar": {}
            },
            "2022-05-17": {
                "GALI": {},
                "Disawar": {}
            },
            "2022-05-18": {
                "GALI": {},
                "Disawar": {}
            },
            "2022-05-19": {
                "GALI": {},
                "Disawar": {}
            },
            "2022-05-20": {
                "GALI": {},
                "Disawar": {}
            },
            "2022-05-21": {
                "GALI": {},
                "Disawar": {}
            },
            "2022-05-22": {
                "GALI": {},
                "Disawar": {}
            },
            "2022-05-23": {
                "GALI": {},
                "Disawar": {}
            },
            "2022-05-24": {
                "GALI": {},
                "Disawar": {}
            },
            "2022-05-25": {
                "GALI": {},
                "Disawar": {}
            },
            "2022-05-26": {
                "GALI": {},
                "Disawar": {}
            },
            "2022-05-27": {
                "GALI": {},
                "Disawar": {}
            },
            "2022-05-28": {
                "GALI": {},
                "Disawar": {}
            },
            "2022-05-29": {
                "GALI": {},
                "Disawar": {}
            },
            "2022-05-30": {
                "GALI": {},
                "Disawar": {}
            },
            "2022-05-31": {
                "GALI": {},
                "Disawar": {}
            }
        }
    }
}
 */