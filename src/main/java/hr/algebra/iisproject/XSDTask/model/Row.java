package hr.algebra.iisproject.XSDTask.model;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;


@XmlRootElement(name = "row")
@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class Row {
    @XmlElement(name = "id")
    private int id;
    @XmlElement(name = "season")
    private int season;
    @XmlElement(name = "week")
    private int week;
    @XmlElement(name = "season_type")
    private String seasonType;
    @XmlElement(name = "start_date")
    private String startDate;
    @XmlElement(name = "start_time_tbd")
    private boolean startTimeTbd;
    private boolean completed;
    @XmlElement(name = "neutral_site")
    private boolean neutralSite;
    @XmlElement(name = "conference_game")
    private boolean conferenceGame;
    @XmlElement(name = "attendance")
    private String attendance;
    @XmlElement(name = "venue_id")
    private int venueId;
    @XmlElement(name = "venue")
    private String venue;
    @XmlElement(name = "home_id")
    private int homeId;
    @XmlElement(name = "home_team")
    private String homeTeam;
    @XmlElement(name = "home_conference")
    private String homeConference;
    @XmlElement(name = "home_division")
    private String homeDivision;
    @XmlElement(name = "home_points")
    private int homePoints;
    @XmlElement(name = "home_post_win_prob")
    private double homePostWinProb;
    @XmlElement(name = "home_pregame_elo")
    private double homePregameElo;
    @XmlElement(name = "home_postgame_elo")
    private double homePostgameElo;
    @XmlElement(name = "away_id")
    private int awayId;
    @XmlElement(name = "away_team")
    private String awayTeam;
    @XmlElement(name = "away_conference")
    private String awayConference;
    @XmlElement(name = "away_division")
    private String awayDivision;
    @XmlElement(name = "away_points")
    private int awayPoints;
    @XmlElement(name = "away_post_win_prob")
    private double awayPostWinProb;
    @XmlElement(name = "away_pregame_elo")
    private double awayPregameElo;
    @XmlElement(name = "away_postgame_elo")
    private double awayPostgameElo;
    @XmlElement(name = "excitement_index")
    private double excitementIndex;


}