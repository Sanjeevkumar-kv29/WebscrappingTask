package com.example.interntask

import android.app.ProgressDialog
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements
import java.io.IOException

private var pD: ProgressDialog? = null
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        pD = ProgressDialog(this)
        pD?.setMessage("FETCHING DATA PLZ WAIT")
        pD?.show()
        getHtmlFromWeb()
    }



    private fun getHtmlFromWeb() {
         Thread(Runnable {
             val PlayerNameArr = ArrayList<String>()
             val LeaderplayerStatsArr = ArrayList<String>()


             val Leaderplayer = ArrayList<String>()
             val LeaderplayerSt = ArrayList<List<String>>()


             try {
                val doc: Document = Jsoup.connect("https://www.espn.com/nfl/qbr").get()

                // val title: String = doc.title()
                //val links: Elements = doc.select("a")

                 val Pnames: Elements = doc.getElementsByClass("Table__TBODY")
                 val Pstats: Elements = doc.getElementsByClass("Table__TR Table__TR--sm Table__even").tagName("tr")


                 for (name in Pnames) {
                     //stringBuilder.append("\n").append("Link :").append(link.attr("href")).append("\n").append("Text :").append(link.text())
                     PlayerNameArr.add(name.getElementsByTag("a").text().toString())
                     Log.d("Playername",PlayerNameArr.toString())

                 }
                 for (stats in Pstats) {
                     LeaderplayerStatsArr.add(stats.getElementsByTag("tr").text().toString())

                 }
                 Log.d("PlayerStats",LeaderplayerStatsArr.toString())
             } catch (e: IOException) {
                 PlayerNameArr.add("Error : "+(e.message)+("\n"))
             }



             runOnUiThread {

                 val parts = PlayerNameArr[0].split(" ")

                 for (i in 0..10 step 2) { Leaderplayer.add(parts[i]+" "+parts[i+1]) }
                 for (i in 0..LeaderplayerStatsArr.size-1) {


                     if (LeaderplayerStatsArr[i].contains(Regex("\\d+(\\.\\d+)*$"))==true) /// finding the leader board value from the scrapped string
                     {

                        // Log.d("pppppppppp",i.toString())  testing can we correctly find the index of the perticular row from web.
                         LeaderplayerSt.add(LeaderplayerStatsArr[i].split(" "))

                     }
                 }


               //  Log.d("LeaderP",LeaderplayerStatsArr[33])
                 Log.d("LeaderP",LeaderplayerSt[0][0])

                 pD?.dismiss()

                 firstplayername.text = "1.${Leaderplayer[0]}"
                 secondplayername.text = "2.${Leaderplayer[1]}"
                 thirdplayername.text = "3.${Leaderplayer[2]}"

                 firstplayerstats.text = "STATS -\n\nQBR -   ${LeaderplayerSt[0][0]} \nPAA -   ${LeaderplayerSt[0][1]} \nPLAYS -   ${LeaderplayerSt[0][2]} \n" +
                         "EPA -   ${LeaderplayerSt[0][3]} \nPASS -   ${LeaderplayerSt[0][4]} \nRUN -   ${LeaderplayerSt[0][5]} \n" +
                         "SACK -   ${LeaderplayerSt[0][6]} \nPEN -   ${LeaderplayerSt[0][7]} \nRAW -   ${LeaderplayerSt[0][8]} \n "

                 secondplayerstats.text = "STATS -\n\nQBR -   ${LeaderplayerSt[1][0]} \nPAA -   ${LeaderplayerSt[1][1]} \nPLAYS -   ${LeaderplayerSt[1][2]} \n" +
                         "EPA -   ${LeaderplayerSt[1][3]} \nPASS -   ${LeaderplayerSt[1][4]} \nRUN -   ${LeaderplayerSt[1][5]} \n" +
                         "SACK -   ${LeaderplayerSt[1][6]} \nPEN -   ${LeaderplayerSt[1][7]} \nRAW -   ${LeaderplayerSt[1][8]} \n "

                 thirdplayerstats.text = "STATS -\n\nQBR -   ${LeaderplayerSt[2][0]} \nPAA -   ${LeaderplayerSt[2][1]} \nPLAYS -   ${LeaderplayerSt[2][2]} \n" +
                         "EPA -   ${LeaderplayerSt[2][3]} \nPASS -   ${LeaderplayerSt[2][4]} \nRUN -   ${LeaderplayerSt[2][5]} \n" +
                         "SACK -   ${LeaderplayerSt[2][6]} \nPEN -   ${LeaderplayerSt[2][7]} \nRAW -   ${LeaderplayerSt[2][8]} \n "



             }
         }).start()
     }
}