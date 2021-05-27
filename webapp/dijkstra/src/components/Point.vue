<template>
    <div class="point-holder" :style="getClass">
        <span class="dot"></span>
        <b>{{object.naam}}</b>
    </div>
</template>

<script>
    export default {
        name: "Point",
        props: ["object"],
        computed: {
            getClass(){
                return {
                    'left' : this.object.x + 'px',
                    'top' : this.object.y + 'px'
                }
            }
        }
    }
</script>

<style scoped>
    .point-holder{
        position: absolute;
    }

    .dot {
        height: 15px;
        width: 15px;
        background-color: #f5f5f5;
        border-radius: 50%;
        display: inline-block;
    }
</style>