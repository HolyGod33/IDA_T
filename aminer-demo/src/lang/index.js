import Vue from 'vue'
import VueI18n from 'vue-i18n'
import axios from 'axios'
import Cookies from 'js-cookie'
import zhLocale from './zh'
import enLocale from './en'

Vue.use(VueI18n)

const messages = {
  ...zhLocale,
  ...enLocale
}

export function getLanguage () {
  const chooseLanguage = Cookies.get('language')
  if (chooseLanguage) return chooseLanguage

  // if has not choose language
  const language = (navigator.language || navigator.browserLanguage).toLowerCase()
  const locales = Object.keys(messages)
  for (const locale of locales) {
    if (language.indexOf(locale) > -1) {
      return locale
    }
  }
  return 'en'
}

const i18n = new VueI18n({
  // set locale
  // options: zh | en
  locale: getLanguage(),
  // set locale messages
  messages
})

export default i18n

export function setI18nLanguage (lang) {
  i18n.locale = lang
  axios.defaults.headers.common['Accept-Language'] = lang
  document.querySelector('html').setAttribute('lang', lang)
  return lang
}
