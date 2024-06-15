# Tennis Scoreboard
A web application that implements a scoreboard for a tennis match.

### Used technologies
1. Database: H2, Hibernate
2. Web-server: Tomcat 10
3. Front-end: JSP, HTML, CSS

### Deployment
- docker build -t app .
- docker run -p8080:8080 app

### Main page
Links leading to the pages for the new match and the list of completed matches

### New match page
Address - `/new-match`

#### Interface
- HTML form 
- Start button (POST request to `/new-match`)

#### POST request handler
- Checks the existence of players in the table `Players`. Creates in absence
- Create an instance of the class `Match`. Saving current match by UUID in the collection
- Redirect to page `/match-score?uuid=$match_id`

### Match score page
Address - `/match-score?uuid=$match_id`

#### Interface
- Table with players names, current score
- HTML forms for count point actions
- Clicking the buttons results in a POST request to the address `/match-score?uuid=$match_id`, the fields of the submitted form contain the ID of the player who won the point

#### POST request handler
- Retrieves an instance of the Match class from the collection
- Updates the match score according to which player wins the point
- If the match has not ended, the match score table is rendered with the buttons described above
- If the match ends:
    - Removing a match from the collection of current matches
    - Writing the completed match to the database
    - Rendering the final score

### Played matches page
Address - `/matches?page=$page_number&filter_by_player_name=$player_name`
- `page` - page number. If the parameter is not specified, the first page is assumed
- `filter_by_player_name` - the name of the player whose matches we are looking for. If the parameter is not specified, all matches are displayed

#### Interface
- Form with filter by player name. Input field for name and “search” button. When clicked, generated GET request `/matches?filter_by_player_name=${NAME}`
- List of found matches
- Page switcher if more matches are found than fits on one page

### Database

#### Table `Players`
<table>
    <tr>
        <th>Column</th>
        <th>Type</th>
        <th>Description</th>
    </tr>
    <tr>
        <td>ID</td>
        <td>Int</td>
        <td>Auto Increment, Primary Key</td>
    </tr>
    <tr>
        <td>Name</td>
        <td>Varchar</td>
        <td>Index</td>
    </tr>
</table>

#### Table `Matches`
<table>
    <tr>
        <th>Column</th>
        <th>Type</th>
        <th>Description</th>
    </tr>
    <tr>
        <td>ID</td>
        <td>Int</td>
        <td>Auto Increment, Primary Key</td>
    </tr>
    <tr>
        <td>Player1</td>
        <td>Int</td>
        <td>References to Players.ID</td>
    </tr>
    <tr>
        <td>Player2</td>
        <td>Int</td>
        <td>References to Players.ID</td>
    </tr>
    <tr>
        <td>Winner</td>
        <td>Int</td>
        <td>References to Players.ID</td>
    </tr>
</table>
