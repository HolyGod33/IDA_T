// 创建一个新样式
const sheet = (function () {
  const style = document.createElement('style')
  style.appendChild(document.createTextNode(''))
  document.head.appendChild(style)
  return style.sheet
})()

// 调整页面大小时，PDF 预览组件会隐藏，打开就会彻底隐藏所有功能变成一个纯预览工具。
function editToolBar () {
  // removeGrowRules()

  // 将功能按钮放在工具栏左侧
  // addElemFromSecondaryToPrimary('pageRotateCcw', 'toolbarViewerLeft')
  // addElemFromSecondaryToPrimary('pageRotateCw', 'toolbarViewerLeft')
  // addElemFromSecondaryToPrimary('zoomIn', 'toolbarViewerLeft')
  // addElemFromSecondaryToPrimary('zoomOut', 'toolbarViewerLeft')

  // 将功能按钮放在工具栏中间
  // addElemFromSecondaryToPrimary('previous', 'toolbarViewerMiddle')
  // addElemFromSecondaryToPrimary('pageNumber', 'toolbarViewerMiddle')
  // addElemFromSecondaryToPrimary('numPages', 'toolbarViewerMiddle')
  // addElemFromSecondaryToPrimary('next', 'toolbarViewerMiddle')

  // 将功能按钮放在工具栏右侧
  // addElemFromSecondaryToPrimary('secondaryOpenFile', 'toolbarViewerRight')

  // 更改功能按钮的图标
  // changeIcon('previous', 'icons/baseline-navigate_before-24px.svg')
  // changeIcon('next', 'icons/baseline-navigate_next-24px.svg')
  // changeIcon('pageRotateCcw', 'icons/baseline-rotate_left-24px.svg')
  // changeIcon('pageRotateCw', 'icons/baseline-rotate_right-24px.svg')
  // changeIcon('viewFind', 'icons/baseline-search-24px.svg')
  // changeIcon('zoomOut', 'icons/baseline-zoom_out-24px.svg')
  // changeIcon('zoomIn', 'icons/baseline-zoom_in-24px.svg')
  // changeIcon('sidebarToggle', 'icons/baseline-toc-24px.svg')
  // changeIcon('secondaryOpenFile', './icons/baseline-open_in_browser-24px.svg')

  // 隐藏某些功能
  // removeElement('secondaryToolbarToggle')
  // removeElement('scaleSelectContainer')
  removeElement('presentationMode')
  removeElement('openFile')
  removeElement('print')
  removeElement('download')
  removeElement('viewBookmark')

  // 去除侧边栏菜单右上角的小绿点
  addCSSRule(sheet, '.pdfSidebarNotification::after', 'display:none !important')
}

function changeIcon (elemID, iconUrl) {
  const element = document.getElementById(elemID)
  let classNames = element.className
  classNames = elemID.includes('Toggle')
    ? 'toolbarButton#' + elemID
    : classNames.split(' ').join('.')
  classNames = elemID.includes('view') ? '#' + elemID + '.toolbarButton' : '.' + classNames
  classNames += '::before'
  addCSSRule(sheet, classNames, `content: url(${iconUrl}) !important`, 0)
}

function addElemFromSecondaryToPrimary (elemID, parentID) {
  const element = document.getElementById(elemID)
  const parent = document.getElementById(parentID)
  element.style.minWidth = '0px'
  element.innerHTML = ''
  parent.append(element)
}

function removeElement (elemID) {
  const element = document.getElementById(elemID)
  element.parentNode.removeChild(element)
}

function removeGrowRules () {
  addCSSRule(sheet, '.hiddenSmallView *', 'display:block !important')
  addCSSRule(sheet, '.hiddenMediumView', 'display:block !important')
  addCSSRule(sheet, '.hiddenLargeView', 'display:block !important')
  addCSSRule(sheet, '.visibleSmallView', 'display:block !important')
  addCSSRule(sheet, '.visibleMediumView', 'display:block !important')
  addCSSRule(sheet, '.visibleLargeView', 'display:block !important')
}

function addCSSRule (sheet, selector, rules, index) {
  if ('insertRule' in sheet) {
    sheet.insertRule(selector + '{' + rules + '}', index)
  } else if ('addRule' in sheet) {
    sheet.addRule(selector, rules, index)
  }
}

window.onload = editToolBar
