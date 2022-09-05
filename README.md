# Asteroid_Radar_app_Sep_2022
Asteroid Radar is an app to view the asteroids detected by NASA that pass near Earth, you can view all the detected asteroids given a period of time with data such as the size, velocity, distance to earth and if they are potentially hazardous. In this project, you will apply the skills such as fetching data from the internet, saving data to a database, and display the data in a clear, compelling UI.

You will need the NEoWs API which is a free, open source API provided by NASA JPL Asteroid team, as they explain it: “Is a RESTful web service for near earth Asteroid information. With NeoWs a user can: search for Asteroids based on their closest approach date to Earth, lookup a specific Asteroid with its NASA JPL small body id, as well as browse the overall data-set.”

The resulting output of the project will be two screens: a Main screen with a list of all the detected asteroids and a Details screen that is going to display the data of that asteroid once it´s selected in the Main screen list. The main screen will also show the NASA image of the day to make the app more striking.

1. Retrofit library to download the data from the Internet.
2. Moshi to convert the JSON data we are downloading to usable data in the form of custom classes.
3. Picasso library to download and cache images (You could also use Glide, but we found it has some issues downloading images from this particular API).
4. RecyclerView to display the asteroids in a list.
5. ViewModel + Room + Data Binding

## Getting Started

The first step is to get an API Key from NASA. Follow the instructions as listed.

Open the starter project in Android Studio

##Steps

1. Got to the following URL - https://api.nasa.gov/ and scroll down a little and you are going to see this:

2. You will use the API key to send requests to NASA servers to get data about asteroids - to view how it works, scroll down a little more until you see the NeoWs data.

3.You are now ready to build a query string with the API like the following, using the API_KEY, START_DATE and END_DATE parameters. https://api.nasa.gov/neo/rest/v1/feed?start_date=START_DATE&end_date=END_DATE&api_key=YOUR_API_KEY
  

<p>

</p>

<table>
  <tr>
    <td>
       <img src = "https://video.udacity-data.com/topher/2020/June/5edeac1d_screen-shot-2020-06-08-at-2.21.53-pm/screen-shot-2020-06-08-at-2.21.53-pm.png"/>
    </td>
  
  </tr>
   <tr>
    <td>
       <img src = "https://video.udacity-data.com/topher/2020/June/5edeac1d_screen-shot-2020-06-08-at-2.21.53-pm/screen-shot-2020-06-08-at-2.21.53-pm.png"/>
    </td>
 
  </tr>
</table>
