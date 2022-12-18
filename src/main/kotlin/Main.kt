import java.math.RoundingMode
import kotlin.math.abs

fun main(args: Array<String>) {
    var randomNumberNT: Int = 0
    var newsType: String = ""
    var randomNumerDemand: Int = 0
    var demand: Int = 0
    var revenueFromSales: Double = 0.0
    var costOfNewsPaper: Double = 0.0
    var lost: Double = 0.0
    var scrapRevenue: Double = 0.0
    var profit: Double = 0.0
    var totalProfit: Double = 0.0
    var orderQuantity: Int = 0

    println("Enter number of iteration")
    var numberOfIteration: Int = readln().toInt()
    println("Enter number of day in every iteration")
    var numberOfDayInIteration: Int = readln().toInt()

    var i: Int = 1
    while (i <= (numberOfIteration * numberOfDayInIteration)) {
        if ((i - 1) % numberOfDayInIteration == 0) {
            println("enter order quantity for iteration number ${(i / numberOfDayInIteration) + 1}")
            orderQuantity = readln().toInt()
            costOfNewsPaper = orderQuantity * 0.33
            totalProfit = 0.0
        }
        println("Enter random number for news type")
        randomNumberNT = readln().toInt()
        newsType = CheackTypeOfNews(randomNumberNT)
        println("Enter random Number for Demand")
        randomNumerDemand = readln().toInt()
        demand = GetDemand(randomNumerDemand, newsType)
        revenueFromSales = if (demand <= orderQuantity) demand * 0.5 else orderQuantity * 0.5
        lost = if (demand > orderQuantity) abs((demand - orderQuantity) * 0.17) else 0.0
        scrapRevenue = if (demand < orderQuantity) abs((demand - orderQuantity) * 0.05) else 0.0
        profit = revenueFromSales + scrapRevenue - lost - costOfNewsPaper
        totalProfit += profit
        println("day\tRNforNT\tNewsType\tRnforDemand\tdemand\tRevenuefromSales\tCostOfNPs\tLostProfit\tScrap Revenue\tDaily profit")
        println(
            "$i\t\t$randomNumberNT\t\t$newsType\t\t$randomNumerDemand\t\t\t$demand\t\t\t$revenueFromSales\t\t\t$costOfNewsPaper\t\t${
                lost.toBigDecimal().setScale(1, RoundingMode.HALF_EVEN)
            }\t\t\t$scrapRevenue\t\t\t\t${profit.toBigDecimal().setScale(1, RoundingMode.HALF_EVEN)}"
        )
        if (i % 10 == 0) {
            println("total profit = %.2f".format(totalProfit) + "$")

        }
        i++

    }


}

fun CheackTypeOfNews(type: Int): String {
    return when (type) {
        in 1..35 -> "Good"
        in 36..80 -> "Fair"
        in 81..100 -> "Poor"
        else -> ""
    }

}

fun GoodProbability(x: Int): Int {
    return when (x) {
        in 1..3 -> 40
        in 4..8 -> 50
        in 9..23 -> 60
        in 24..43 -> 70
        in 44..78 -> 80
        in 79..93 -> 90
        in 94..100 -> 100
        else -> 0
    }
}

fun FairProbability(x: Int): Int {
    return when (x) {
        in 1..10 -> 40
        in 11..28 -> 50
        in 29..68 -> 60
        in 69..88 -> 70
        in 89..96 -> 80
        in 97..100 -> 90
        else -> 0
    }
}

fun PoorProbability(x: Int): Int {
    return when (x) {
        in 1..44 -> 40
        in 45..66 -> 50
        in 67..82 -> 60
        in 83..94 -> 70
        in 95..100 -> 80
        else -> 0
    }
}

fun GetDemand(x: Int, type: String): Int {
    return if (type.equals("good", ignoreCase = true)) {
        GoodProbability(x)
    } else if (type.equals("fair", ignoreCase = true)) {
        FairProbability(x)
    } else {
        PoorProbability(x)
    }
}