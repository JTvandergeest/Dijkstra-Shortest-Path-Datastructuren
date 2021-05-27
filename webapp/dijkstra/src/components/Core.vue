<template>
  <div class="page">
    <div class="image_holder page-half">
      <img src="../assets/nederland-map.png"/>
      <point :object="punt" v-for="punt in punten" v-bind:key="punt.naam"/>
    </div>
    <div class="page-half form">
      <b>Reistype: </b>
      <select v-model="berekenObj.soortGewicht">
        <option value="0">Autorit</option>
        <option value="1">Treinrit</option>
      </select>
      <br>
      <b>Van</b>
      <input v-model="berekenObj.startpunt"/>
      <b>Naar</b>
      <input v-model="berekenObj.eindpunt"/>
      <button @click="bereken">Bereken</button>
      <b>Route</b>
      <div class="mini-form" v-if="route">
        <span v-for="(r, indx) in route.route"  :key="r">
          {{indx}}. {{stadsNaam(r)}}
        </span>
      </div>
      <div class="mini-form" v-if="route">
        <span v-for="stap in route.stappen"  :key="stap">
          {{stadsNaam(stap.van)}} -> {{stadsNaam(stap.naar)}} ({{stap.lengte}} {{soortGewichtLabel}})
        </span>
      </div>
      <div class="mini-form" v-if="route">
        <b>Totaal: {{totaalBerekend}} {{soortGewichtLabel}}</b>
      </div>
      <span style="color:red" v-if="errMsg">{{errMsg}}</span>
    </div>
  </div>
</template>

<script>
import Point from "./Point";
import axios from 'axios';
import _ from 'lodash';

export default {
  name: 'Core',
  components: {Point},
  props: {
    msg: String
  },
  data(){
    return {
      punten: [],
      berekenObj: {},
      steden: [],
      route: [],
      errMsg: ''
    }
  },
  computed: {
    totaalBerekend(){
      return _.sumBy(this.route.stappen, (o) => { return o.lengte; });
    },
    soortGewichtLabel(){
      return (this.berekenObj.soortGewicht == 0) ? "Kms" : "Minuten";
    }
  },
  methods: {
    stadsNaam(n){
      return _.find(this.steden, {positie: n}).naam
    },
    bereken(){
      axios.post(`http://localhost:8080/netwerk/bereken`, this.berekenObj)
        .then((res) => {
          this.errMsg = "";
          this.route = res.data;
        })
        .catch((ex) => {
          this.errMsg = "Error " +  ex.message
        })
    },
    getSteden(){
      axios.get(`http://localhost:8080/netwerk/steden`, this.berekenObj)
        .then((res) => {
          this.errMsg = "";
          this.steden = res.data;
        })
        .catch((ex) => {
          this.errMsg = "Error " +  ex.message
        })
    }
  },

  mounted() {
    let amsterdam = {
      naam: "Amsterdam",
      x : 160,
      y : 200
    };
    let groningen = {
      naam: "Groningen",
      x : 330,
      y : 50
    };
    let zwolle = {
      naam: "Zwolle",
      x : 260,
      y : 190
    };
    let utrecht = {
      naam: "Utrecht",
      x : 180,
      y : 230
    };
    let leeuwarden = {
      naam: "Leeuwarden",
      x : 260,
      y : 50
    };
    let lelystad = {
      naam: "Lelystad",
      x : 210,
      y : 170
    };
    let alkmaar = {
      naam: "Alkmaar",
      x : 140,
      y : 140
    };
    let denhaag = {
      naam: "Den Haag",
      x : 80,
      y : 260
    };
    let rotterdam = {
      naam: "Rotterdam",
      x : 95,
      y : 280
    };
    this.punten.push(alkmaar);
    this.punten.push(lelystad);
    this.punten.push(leeuwarden);
    this.punten.push(amsterdam);
    this.punten.push(groningen);
    this.punten.push(zwolle);
    this.punten.push(utrecht);
    this.punten.push(denhaag);
    this.punten.push(rotterdam);

    this.getSteden();
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style  scoped>
  .page{
    display: flex;
    background-color: #c9cfd6;
    border-radius: 10px;
    padding: 20px;
    min-height: 500px;
  }

  .image_holder{
    min-width: 400px;
    display: flex;
    position: relative;
  }

  .image_holder img{
    width: 400px;
    position: absolute;
  }

  .form{
    padding-left: 20px;
    display: flex;
    flex-direction: column;
    flex-basis: 300px;
    text-align: left;
  }

  .page-half{
    width: 50% !important
  }

  .mini-form{
    padding: 10px;
    background-color: white;
    margin-top: 10px;
    border-radius: 5px;
    display: flex;
    flex-direction: column;
    text-align: left;
  }

  .mini-form span{
    background-color: #dbdbdb;
    padding: 3px;
    border-radius: 4px;
    margin-top: 4px;
  }
</style>
