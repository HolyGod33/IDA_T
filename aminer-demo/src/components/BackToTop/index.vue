<template>
  <transition :name="transitionName">
    <div v-show="visible" :style="customStyle" class="back-to-ceiling" @click="backToTop">
      <span>返回顶部</span>
    </div>
  </transition>
</template>

<script>
export default {
  name: 'BackToTop',
  props: {
    visibilityHeight: {
      type: Number,
      default: 400
    },
    backPosition: {
      type: Number,
      default: 0
    },
    customStyle: {
      type: Object,
      default: function () {
        return {
          right: '50px',
          bottom: '50px',
          width: '40px',
          height: '40px',
          'border-radius': '4px',
          'line-height': '45px',
          background: '#e7eaf1'
        }
      }
    },
    transitionName: {
      type: String,
      default: 'fade'
    }
  },
  data () {
    return {
      visible: false,
      interval: null,
      isMoving: false
    }
  },
  mounted () {
    window.addEventListener('scroll', this.handleScroll)
  },
  beforeDestroy () {
    window.removeEventListener('scroll', this.handleScroll)
    if (this.interval) {
      clearInterval(this.interval)
    }
  },
  methods: {
    handleScroll () {
      this.visible = window.pageYOffset > this.visibilityHeight
    },
    backToTop () {
      if (this.isMoving) return
      const start = window.pageYOffset
      let i = 0
      this.isMoving = true
      this.interval = setInterval(() => {
        const next = Math.floor(this.easeInOutQuad(10 * i, start, -start, 500))
        if (next <= this.backPosition) {
          window.scrollTo(0, this.backPosition)
          clearInterval(this.interval)
          this.isMoving = false
        } else {
          window.scrollTo(0, next)
        }
        i++
      }, 5)
    },
    easeInOutQuad (t, b, c, d) {
      if ((t /= d / 2) < 1) return c / 2 * t * t + b
      return -c / 2 * (--t * (t - 2) - 1) + b
    }
  }
}
</script>

<style lang="scss" scoped>
.back-to-ceiling {
  position: fixed;
  z-index: 999;
  display: inline-block;
  text-align: center;
  cursor: pointer;
  background: url(data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAEkAAAAXCAYAAABH92JbAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAA2hpVFh0WE1MOmNvbS5hZG9iZS54bXAAAAAAADw/eHBhY2tldCBiZWdpbj0i77u/IiBpZD0iVzVNME1wQ2VoaUh6cmVTek5UY3prYzlkIj8+IDx4OnhtcG1ldGEgeG1sbnM6eD0iYWRvYmU6bnM6bWV0YS8iIHg6eG1wdGs9IkFkb2JlIFhNUCBDb3JlIDUuMy1jMDExIDY2LjE0NTY2MSwgMjAxMi8wMi8wNi0xNDo1NjoyNyAgICAgICAgIj4gPHJkZjpSREYgeG1sbnM6cmRmPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5LzAyLzIyLXJkZi1zeW50YXgtbnMjIj4gPHJkZjpEZXNjcmlwdGlvbiByZGY6YWJvdXQ9IiIgeG1sbnM6eG1wTU09Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC9tbS8iIHhtbG5zOnN0UmVmPSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAvc1R5cGUvUmVzb3VyY2VSZWYjIiB4bWxuczp4bXA9Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC8iIHhtcE1NOk9yaWdpbmFsRG9jdW1lbnRJRD0ieG1wLmRpZDowMjgwMTE3NDA3MjA2ODExODA4M0UyNDA4ODdDRTZBQiIgeG1wTU06RG9jdW1lbnRJRD0ieG1wLmRpZDpBQjc5RkRFNkI5ODMxMUUzQkZGNDhENEJFQjM2OTcyRiIgeG1wTU06SW5zdGFuY2VJRD0ieG1wLmlpZDpBQjc5RkRFNUI5ODMxMUUzQkZGNDhENEJFQjM2OTcyRiIgeG1wOkNyZWF0b3JUb29sPSJBZG9iZSBQaG90b3Nob3AgQ1M2IChNYWNpbnRvc2gpIj4gPHhtcE1NOkRlcml2ZWRGcm9tIHN0UmVmOmluc3RhbmNlSUQ9InhtcC5paWQ6MDI4MDExNzQwNzIwNjgxMTgwODNFMjQwODg3Q0U2QUIiIHN0UmVmOmRvY3VtZW50SUQ9InhtcC5kaWQ6MDI4MDExNzQwNzIwNjgxMTgwODNFMjQwODg3Q0U2QUIiLz4gPC9yZGY6RGVzY3JpcHRpb24+IDwvcmRmOlJERj4gPC94OnhtcG1ldGE+IDw/eHBhY2tldCBlbmQ9InIiPz5HPYM5AAACC0lEQVR42uyZTSgEYRjH90NoqaVcpDblvKW2XOSkNh8H5Yac9sBBDqQokiJKSMpNTi5OPo6Ktsgu67JcUHIXWfK1u3b8Xx6Zxuzu+64dM83uv34zNfu8b8/8Z+d5P8YqSZLFJNLsRmyWvIRMKgVD4AhEwA3YAV0pzGwEqyAEwmAddIICHe7FqhnsdQM14FxKLj+ooFiGE2ykiD8GVbJ4I1ELAnTmasMODnAhpVcIFIMicMgRH6Z4IxnUAh4pP3Zu5TVpUOLXDJgWiB8wkEF9IK7IL07X05oUFLjpZ4JXQQOYYwfzafJcoDjVPqw43KE4lWtUTF+AQ8eBqQSsgTaO2E0apJ7URrdCDZOM62hQJfBzGmShOD+1+2XSiYaJ7utkkBsEgEewnYfauZUmzWmU6DuY0sEgLzgArgzbu6i992cu/1WclqXsa1iHIt0DYlnKP0b9fRZu5pWdZs7dWXqa7B80+s//oF5aMSjflGqBPq5BQnFtVv4UmGHjIPEH96PAZ6C5kVMwf6daPzbFKnqCqvx9Bk/yFjSBFTMvcL+1DerAmUA/p9Rm1+y7AHJdgnqwx9EHm1s0gKtc2CpR6gE00/ZHMm3RKxbJlf0kNb2BDrCk8hsbDdvBay5tuiUTGxL7waTs2iLw0YTR9BLZQRwDUVqwjuTS9q3VRB8C1OQUnM6UqdXX/IcADn0IMADs2AqDOPSutQAAAABJRU5ErkJggg==) no-repeat -42px center #666;

  span {
    display: none;
  }
}

.back-to-ceiling:hover {
  padding-top: 3px;
  line-height: 17px;
  background-position: -100px 0;
  background-color: rgb(64, 64, 64);
  font-size: 14px;
  color: #fff;
  white-space: normal;

  span {
    display: block;
  }
}

.fade-enter-active,
.fade-leave-active {
  transition: opacity .5s;
}

.fade-enter,
.fade-leave-to {
  opacity: 0
}

.back-to-ceiling .Icon {
  fill: #9aaabf;
  background: none;
}
</style>
