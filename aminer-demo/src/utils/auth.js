import { TokenKey, UserKey } from '@/constant'

export function getToken () {
  return localStorage.getItem(TokenKey)
}

export function setToken (token) {
  return localStorage.setItem(TokenKey, token)
}

export function removeToken () {
  return localStorage.removeItem(TokenKey)
}

export function getUserKey () {
  return localStorage.getItem(UserKey)
}

export function setUserKey (token) {
  return localStorage.setItem(UserKey, token)
}

export function removeUserKey () {
  return localStorage.removeItem(UserKey)
}
