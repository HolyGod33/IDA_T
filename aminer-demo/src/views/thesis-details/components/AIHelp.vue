<template>
  <div class="container">
    <h2 class="AIHelp">
      <span>{{ $t('thesisDetails.AIHelp') }}</span>
    </h2>

    <div class="video">
      <h2 class="title">
        <span>{{ $t('thesisDetails.AIGenerate.title') }}</span>
      </h2>
      <p class="desc">{{ $t('thesisDetails.AIGenerate.contents') }}</p>
      <hr>
      <div class="imgBlock">
        <img src="https://originalfileserver.aminer.cn/sys/aminer/pubs/createVideo.jpg" alt="pub">
        <div class="videoIcon">
          <svg-icon icon-class="video" class-name="icon_video"/>
          <span>{{ $t('thesisDetails.AIGenerate.goGenerating') }}</span>
        </div>
      </div>
    </div>

    <div class="mrtBlock">
      <h2 class="title">
        <span>{{ $t('thesisDetails.AITraceability.title') }}</span>
      </h2>
      <p class="desc">{{ $t('thesisDetails.AITraceability.contents') }}</p>
      <hr>
      <div class="panel">
        <div class="mrtZone">
          <img class="mrtPre" title="Master Reading Tree" alt="Master Reading Tree"
               src="https://originalfileserver.aminer.cn/sys/aminer/pubs/mrt_preview.jpeg">
          <div class="mrtText">
            <span class="textInner">
              <span>{{ $t('thesisDetails.AITraceability.generateMRT') }}</span>
            </span>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {}
</script>

<style lang="scss" scoped>
p {
  padding-top: 0;
}

h1, h2, h3, h4 {
  margin: 0;
  font-family: "Open Sans", Arial, Helvetica, Sans-Serif;
  font-weight: 300;
}

.container {
  --legend_bottom: 10px;
  width: var(--width_left);

  .AIHelp {
    font-size: 1.33333333em;
    font-family: Roboto-Regular, Roboto;
    font-weight: 400;
    color: #00193a;
    margin-bottom: var(--legend_bottom);
  }
}

.video {
  padding: 14px 20px;
  border: 1px solid #dadada;

  .title {
    font-size: 16px;
    font-family: HiraginoSansGB-W6, HiraginoSansGB;
    color: #00193a;
    line-height: 24px;
  }

  .desc {
    font-size: 14px;
    font-family: PingFangSC-Regular, PingFang SC;
    font-weight: 400;
    color: #666;
    line-height: 20px;
    margin-top: 0;
    margin-bottom: 0;
  }

  hr {
    border-color: #dadada;
  }

  .imgBlock {
    position: relative;

    .videoIcon {
      cursor: pointer;
      position: absolute;
      height: 100%;
      width: 100%;
      display: flex;
      align-items: center;
      justify-content: center;
      flex-direction: column;
      top: 0;

      .icon_video {
        width: 49px;
        height: 49px;
        color: rgba(0, 0, 0, .5);
      }

      span {
        font-size: 14px;
        font-family: PingFangSC-Regular, PingFang SC;
        font-weight: 400;
        color: #000;
        line-height: 20px;
        margin-top: 10px;
      }
    }
  }

  img {
    opacity: .19;
    width: 100%;
  }
}

.mrtBlock {
  border: 1px solid #dadada;
  margin-top: 12px;
  padding: 14px 20px;

  .title {
    font-size: 16px;
    font-family: HiraginoSansGB-W6, HiraginoSansGB;
    color: #00193a;
    line-height: 24px;
  }

  .desc {
    font-size: 14px;
    font-family: PingFangSC-Regular, PingFang SC;
    font-weight: 400;
    color: #666;
    line-height: 20px;
    margin-top: 0;
    margin-bottom: 0;
  }
}

.panel {
  border: 1px solid #eee;
  box-shadow: 2px 2px 4px hsla(0, 0%, 92.5%, .5);
  margin-bottom: 20px;
  margin-right: 16px;
}

.mrtZone {
  position: relative;

  .mrtPre {
    width: 100%;
  }

  .mrtText {
    position: absolute;
    top: 0;
    width: 100%;
    height: 100%;
    text-align: center;
    font-size: 26px;
    cursor: pointer;
    background-color: hsla(0, 0%, 100%, .4);
    display: flex;
    align-items: center;
    justify-content: center;

    .textInner {
      border: 1px solid #e8e8e8;
      box-shadow: 1px 1px 1px #eee;
      background-color: hsla(0, 0%, 100%, .5);
      padding: 4px 6px;
    }
  }
}
</style>
