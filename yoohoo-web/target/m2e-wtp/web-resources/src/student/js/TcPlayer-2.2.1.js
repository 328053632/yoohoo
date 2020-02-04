! function(e, t) {
	if("object" == typeof exports && "object" == typeof module) module.exports = t();
	else if("function" == typeof define && define.amd) define([], t);
	else {
		var i = t();
		for(var o in i)("object" == typeof exports ? exports : e)[o] = i[o]
	}
}(this, function() {
	return function(e) {
		function t(o) {
			if(i[o]) return i[o].exports;
			var n = i[o] = {
				exports: {},
				id: o,
				loaded: !1
			};
			return e[o].call(n.exports, n, n.exports, t), n.loaded = !0, n.exports
		}
		var i = {};
		return t.m = e, t.c = i, t.p = "//imgcache.qq.com/open/qcloud/video/vcplayer/", t(0)
	}([function(e, t, i) {
		"use strict";

		function o(e) {
			if(e && e.__esModule) return e;
			var t = {};
			if(null != e)
				for(var i in e) Object.prototype.hasOwnProperty.call(e, i) && (t[i] = e[i]);
			return t["default"] = e, t
		}

		function n(e, t) {
			if(!(e instanceof t)) throw new TypeError("Cannot call a class as a function")
		}

		function r(e, t) {
			if(!e) throw new ReferenceError("this hasn't been initialised - super() hasn't been called");
			return !t || "object" != typeof t && "function" != typeof t ? e : t
		}

		function s(e, t) {
			if("function" != typeof t && null !== t) throw new TypeError("Super expression must either be null or a function, not " + typeof t);
			e.prototype = Object.create(t && t.prototype, {
				constructor: {
					value: e,
					enumerable: !1,
					writable: !0,
					configurable: !0
				}
			}), t && (Object.setPrototypeOf ? Object.setPrototypeOf(e, t) : e.__proto__ = t)
		}

		function l(e, t) {
			if(d.IS_MOBILE ? (e.flash = !1, d.IS_X5TBS && e.x5_player ? w.mobile = ["flv", "m3u8", "mp4"] : d.IS_ENABLED_MSE && e.h5_flv && (w.mobile = ["flv", "m3u8", "mp4"])) : (e.flash = !(void 0 != e.flash && !t.isFormat("rtmp")) || e.flash, e.flash ? (!d.IS_ENABLED_FLASH || d.IS_MAC && d.IS_SAFARI) && (e.flash = !1, d.IS_ENABLED_MSE ? e.h5_flv && (d.IS_SAFARI && v.compareVersion(d.SAFARI_VERSION, "10.1") > -1 || !d.IS_SAFARI) ? w.pc = ["flv", "m3u8", "mp4"] : w.pc = ["m3u8", "mp4"] : w.pc = ["mp4"]) : d.IS_ENABLED_MSE ? e.h5_flv && (d.IS_SAFARI && v.compareVersion(d.SAFARI_VERSION, "10.1") > -1 || !d.IS_SAFARI) ? w.pc = ["flv", "m3u8", "mp4"] : w.pc = ["m3u8", "mp4"] : !d.IS_ENABLED_FLASH || d.IS_MAC && d.IS_SAFARI ? w.pc = ["mp4"] : e.flash = !0), e.clarity) {
				var i = S.indexOf(e.clarity);
				S.splice(i, 1), S.unshift(e.clarity)
			}
		}

		function a(e) {
			var t = {
				urls: {
					m3u8: {
						od: e.m3u8 || "",
						hd: e.m3u8_hd || "",
						sd: e.m3u8_sd || ""
					},
					flv: {
						od: e.flv || "",
						hd: e.flv_hd || "",
						sd: e.flv_sd || ""
					},
					mp4: {
						od: e.mp4 || "",
						hd: e.mp4_hd || "",
						sd: e.mp4_sd || ""
					},
					rtmp: {
						od: e.rtmp || "",
						hd: e.rtmp_hd || "",
						sd: e.rtmp_sd || ""
					}
				},
				isClarity: function(e) {
					var i = t.urls;
					return !!(i.m3u8[e] || i.flv[e] || i.mp4[e] || i.rtmp[e])
				},
				isFormat: function(e) {
					var i = t.urls;
					return !!i[e].od || !!i[e].hd || !!i[e].sd
				},
				hasUrl: function() {
					return this.isFormat("rtmp") || this.isFormat("flv") || this.isFormat("m3u8") || this.isFormat("mp4")
				}
			};
			t.definitions = [];
			for(var i = 0; i < S.length; i++) t.isClarity(S[i]) && t.definitions.push(S[i]);
			l(e, t);
			var o = p(t);
			return o && (t.curUrl = o.url, t.curDef = o.definition, t.curFormat = o.format), t
		}

		function c(e, t, i) {
			var o = arguments.length > 3 && void 0 !== arguments[3] ? arguments[3] : w,
				n = "",
				r = void 0;
			i = i || (d.IS_MOBILE ? o.mobile : o.pc);
			for(var s = 0; s < i.length; s++)
				if(n = i[s], e[n][t]) {
					r = {
						definition: t,
						url: e[n][t],
						format: n
					};
					break
				}
			return r
		}

		function u(e, t) {
			for(var i = arguments.length > 2 && void 0 !== arguments[2] ? arguments[2] : S, o = "", n = 0; n < i.length; n++)
				if(o = i[n], e[t][o]) return {
					definition: o,
					url: e[t][o]
				}
		}

		function p(e) {
			for(var t = arguments.length > 1 && void 0 !== arguments[1] ? arguments[1] : w, i = void 0, o = "", n = e.urls, r = d.IS_MOBILE ? t.mobile : t.pc, s = 0; s < r.length; s++)
				if(o = r[s], e.isFormat(o)) {
					i = u(n, o), i.format = o;
					break
				}
			return i
		}
		t.__esModule = !0, t.TcPlayer = void 0;
		var h = i(1),
			d = o(h),
			f = i(2),
			y = (o(f), i(3)),
			v = o(y),
			m = i(4),
			g = o(m),
			b = i(5),
			_ = g.MSG,
			w = {
				mobile: ["m3u8", "mp4"],
				pc: ["rtmp", "flv", "m3u8", "mp4"]
			},
			S = ["od", "hd", "sd"];
		t.TcPlayer = function(e) {
			function t(i, o) {
				n(this, t);
				var s = a(o),
					l = {
						owner: i,
						videoSource: s,
						src: s.curUrl,
						autoplay: o.autoplay,
						live: o.live,
						flash: o.flash,
						flashUrl: o.flashUrl,
						poster: o.coverpic,
						width: o.width,
						height: o.height,
						volume: o.volume,
						listener: o.listener,
						wording: o.wording,
						controls: o.controls,
						clarity: o.clarity,
						clarityLabel: o.clarityLabel,
						showLoading: "boolean" != typeof o.showLoading || o.showLoading,
						coverpic_pause: void 0 === o.coverpic_pause || o.coverpic_pause,
						fullscreenEnabled: void 0 === o.fuScrnEnabled || o.fuScrnEnabled,
						systemFullscreen: o.systemFullscreen || !1,
						hls: o.hls || "0.8.9",
						h5_flv: o.h5_flv,
						x5_player: o.x5_player,
						x5_type: o.x5_type,
						x5_fullscreen: o.x5_fullscreen,
						x5_orientation: o.x5_orientation,
						x5_playsinline: o.x5_playsinline,
						preload: o.preload || "auto",
						hls_autostartload: o.hls_autostartload !== !1
					};
				return r(this, e.call(this, l))
			}
			return s(t, e), t.prototype._switchClarity = function(e) {
				e = e || "od";
				var t = this.currentTime(),
					i = this.options.videoSource,
					o = c(i.urls, e),
					n = this.playing();
				this.load(o.url), i.curUrl = o.url, i.curDef = o.definition, i.curFormat = o.format;
				var r = v.bind(this, function() {
					parseInt(this.duration() - t) > 0 && !this.options.live && this.currentTime(t), n && this.play(!0), g.unsub(_.MetaLoaded, "*", r, this)
				});
				g.sub(_.MetaLoaded, "*", r, this)
			}, t.prototype.switchClarity = function(e) {
				this.claritySwitcher.setClarity(e)
			}, t.prototype.handleMsg = function(t) {
				e.prototype.handleMsg.call(this, t)
			}, t
		}(b.Player)
	}, function(e, t) {
		"use strict";
		t.__esModule = !0;
		var i = window.navigator.userAgent,
			o = /AppleWebKit\/([\d.]+)/i.exec(i),
			n = o ? parseFloat(o.pop()) : null,
			r = t.IS_IPAD = /iPad/i.test(i),
			s = t.IS_IPHONE = /iPhone/i.test(i) && !r,
			l = t.IS_IPOD = /iPod/i.test(i),
			a = t.IS_IOS = s || r || l,
			c = (t.IOS_VERSION = function() {
				var e = i.match(/OS (\d+)_/i);
				if(e && e[1]) return e[1]
			}(), t.IS_MAC = /Mac/i.test(i), t.IS_ANDROID = /Android/i.test(i)),
			u = t.ANDROID_VERSION = function() {
				var e, t, o = i.match(/Android (\d+)(?:\.(\d+))?(?:\.(\d+))*/i);
				return o ? (e = o[1] && parseFloat(o[1]), t = o[2] && parseFloat(o[2]), e && t ? parseFloat(o[1] + "." + o[2]) : e ? e : null) : null
			}(),
			p = (t.IS_OLD_ANDROID = c && /webkit/i.test(i) && u < 2.3, t.IS_NATIVE_ANDROID = c && u < 5 && n < 537, t.IS_FIREFOX = /Firefox/i.test(i), t.IS_EDGE = /Edge/i.test(i)),
			h = t.IS_CHROME = !p && /Chrome/i.test(i),
			d = t.IS_SAFARI = !p && !h && /Safari/i.test(i),
			f = (t.SAFARI_VERSION = function() {
				if(!d) return null;
				var e = /version\/([\d.]+)/i,
					t = i.match(e);
				return t ? t[1] : void 0
			}(), t.IS_IE8 = /MSIE\s8\.0/.test(i), t.IS_IE9 = /MSIE\s9\.0/.test(i), t.IS_IE = /(msie\s|trident.*rv:)([\w.]+)/i.test(i)),
			y = (t.IE_VERSION = function() {
				var e = /(msie\s|trident.*rv:)([\w.]+)/i,
					t = i.match(e);
				return t ? t[2] : null
			}(), t.TOUCH_ENABLED = !!("ontouchstart" in window || window.DocumentTouch && document instanceof window.DocumentTouch), t.BACKGROUND_SIZE_SUPPORTED = "backgroundSize" in document.createElement("video").style, t.HASVIDEO = !!document.createElement("video").canPlayType, t.IS_X5TBS = /TBS\/\d+/i.test(i)),
			v = (t.TBS_VERSION = function() {
				var e = i.match(/TBS\/(\d+)/i);
				if(e && e[1]) return e[1]
			}(), t.IS_MQQB = !y && /MQQBrowser\/\d+/i.test(i), t.IS_MOBILE = c || a, t.IS_FILE_PROTOCOL = /file:/.test(location.protocol), t.FLASH_VERSION = null);
		t.IS_ENABLED_FLASH = function() {
			var e;
			if(document.all || f) try {
				if(e = new ActiveXObject("ShockwaveFlash.ShockwaveFlash")) return t.FLASH_VERSION = v = e.GetVariable("$version").split(" ")[1].replace(/,/g, "."), window.console && console.log("FLASH_VERSION", v), !0
			} catch(i) {
				return !1
			} else try {
				if(navigator.plugins && navigator.plugins.length > 0 && (e = navigator.plugins["Shockwave Flash"])) {
					for(var o = e.description.split(" "), n = 0; n < o.length; ++n) isNaN(parseInt(o[n])) || (t.FLASH_VERSION = v = o[n], window.console && console.log("FLASH_VERSION", parseInt(o[n])));
					return !0
				}
			} catch(i) {
				return !1
			}
			return !1
		}(), t.IS_ENABLED_MSE = function() {
			return window.MediaSource = window.MediaSource || window.WebKitMediaSource, window.MediaSource && "function" == typeof window.MediaSource.isTypeSupported && window.MediaSource.isTypeSupported('video/mp4; codecs="avc1.42E01E,mp4a.40.2"')
		}()
	}, function(e, t) {
		"use strict";

		function i(e, t, i) {
			return e ? (e.addEventListener ? e.addEventListener(t, i, !1) : e.attachEvent && e.attachEvent("on" + t, i), i) : console.warn("element not exists")
		}

		function o(e, t, i) {
			return e ? void(e.removeEventListener ? e.removeEventListener(t, i, !1) : e.detachEvent && e.detachEvent("on" + t, i)) : console.warn("element not exists")
		}

		function n() {
			var e = arguments.length > 0 && void 0 !== arguments[0] ? arguments[0] : "div",
				t = arguments.length > 1 && void 0 !== arguments[1] ? arguments[1] : {},
				i = arguments.length > 2 && void 0 !== arguments[2] ? arguments[2] : {},
				o = document.createElement(e);
			for(var n in t)
				if(t.hasOwnProperty(n)) {
					var r = t[n];
					null === r ? o.removeAttribute(r) : o.setAttribute(n, r)
				}
			for(var s in i) i.hasOwnProperty(s) && (o[s] = i[s]);
			return o
		}

		function r(e) {
			return document.getElementById(e)
		}

		function s(e, t) {
			e.classList ? e.classList.add(t) : c(e, t) || (e.className = e.className + " " + t)
		}

		function l(e, t) {
			e.classList ? e.classList.remove(t) : e.className = e.className.replace(u(t), " ")
		}

		function a(e, t, i) {
			i ? s(e, t) : l(e, t)
		}

		function c(e, t) {
			return e.classList ? e.classList.contains(t) : u(t).test(e.className)
		}

		function u(e) {
			return new RegExp("(^|\\s)" + e + "($|\\s)")
		}

		function p(e) {
			var t = void 0;
			if(e.getBoundingClientRect && e.parentNode && (t = e.getBoundingClientRect()), !t) return {
				left: 0,
				top: 0
			};
			var i = document.documentElement,
				o = document.body,
				n = i.clientLeft || o.clientLeft || 0,
				r = window.pageXOffset || o.scrollLeft,
				s = t.left + r - n,
				l = i.clientTop || o.clientTop || 0,
				a = window.pageYOffset || o.scrollTop,
				c = t.top + a - l;
			return {
				left: Math.round(s),
				top: Math.round(c)
			}
		}

		function h(e, t, i) {
			var o = {},
				n = i || p(e),
				r = e.offsetWidth,
				s = e.offsetHeight,
				l = n.top,
				a = n.left,
				c = t.pageY || t.clientY,
				u = t.pageX || t.clientX;
			return t.changedTouches && (u = t.changedTouches[0].pageX, c = t.changedTouches[0].pageY), o.y = Math.max(0, Math.min(1, (l - c + s) / s)), o.x = Math.max(0, Math.min(1, (u - a) / r)), o
		}

		function d(e, t, i) {
			var o = arguments.length > 3 && void 0 !== arguments[3] && arguments[3],
				n = document.createElement("script");
			if(n.onload = n.onreadystatechange = function() {
					this.readyState && "loaded" !== this.readyState && "complete" !== this.readyState || ("function" == typeof t && t(), n.onload = n.onreadystatechange = null, n.parentNode && !o && n.parentNode.removeChild(n))
				}, i)
				for(var r in i)
					if(i.hasOwnProperty(r)) {
						var s = i[r];
						null === s ? n.removeAttribute(s) : n.setAttribute(r, s)
					}
			n.src = e, document.getElementsByTagName("head")[0].appendChild(n)
		}

		function f() {
			var e = document,
				t = e.documentElement,
				i = e.body;
			return {
				width: t && t.clientWidth || i && i.offsetWidth || window.innerWidth || 0,
				height: t && t.clientHeight || i && i.offsetHeight || window.innerHeight || 0
			}
		}
		t.__esModule = !0, t.on = i, t.off = o, t.createEl = n, t.get = r, t.addClass = s, t.removeClass = l, t.toggleClass = a, t.hasClass = c, t.findElPosition = p, t.getPointerPosition = h, t.loadScript = d, t.getViewportSize = f
	}, function(e, t, i) {
		"use strict";

		function o(e) {
			if(e && e.__esModule) return e;
			var t = {};
			if(null != e)
				for(var i in e) Object.prototype.hasOwnProperty.call(e, i) && (t[i] = e[i]);
			return t["default"] = e, t
		}

		function n() {
			return w++
		}

		function r(e, t, i) {
			t.guid || (t.guid = n());
			var o = function() {
				t.apply(e, arguments)
			};
			return o.guid = i ? i + "_" + t.guid : t.guid, o
		}

		function s(e) {
			if(e instanceof Array) return 0 === e.length;
			for(var t in e)
				if(e.hasOwnProperty(t)) return !1;
			return !0
		}

		function l(e) {
			e = 0 | e;
			var t = 3600,
				i = 60,
				o = e / t | 0,
				n = (e - o * t) / i | 0,
				r = e - o * t - n * i;
			return o = o > 0 ? o + ":" : "", n = n > 0 ? n + ":" : "00:", r = r > 0 ? r + "" : o.length > 0 || n.length > 0 ? "00" : "00:00", o = 2 == o.length ? "0" + o : o, n = 2 == n.length ? "0" + n : n, r = 1 == r.length ? "0" + r : r, o + n + r
		}

		function a(e) {
			p.__isFullscreen = !!document[S.fullscreenElement], p.__isFullscreen || m.off(document, S.fullscreenchange, a), b.pub({
				type: g.MSG.FullScreen,
				src: "util",
				ts: e.timeStamp,
				detail: {
					isFullscreen: p.__isFullscreen
				}
			}, p.player)
		}

		function c(e, t) {
			m.off(t.video.el, "webkitbeginfullscreen", c), "webkitbeginfullscreen" == e.type ? (m.on(t.video.el, "webkitendfullscreen", function(e) {
				c(e, t)
			}), b.pub({
				type: g.MSG.FullScreen,
				src: "util",
				ts: e.timeStamp,
				detail: {
					isFullscreen: !0
				}
			}, p.player)) : "webkitendfullscreen" == e.type && (m.off(t.video.el, "webkitendfullscreen", c), b.pub({
				type: g.MSG.FullScreen,
				src: "util",
				ts: e.timeStamp,
				detail: {
					isFullscreen: !1
				}
			}, p.player))
		}

		function u(e) {
			27 === e.keyCode && p(p.player, !1)
		}

		function p(e, t, i) {
			if("undefined" == typeof t) return p.__isFullscreen || !1;
			var o = e.options.systemFullscreen;
			p.player = e, S.requestFullscreen ? t ? (m.on(document, S.fullscreenchange, a), i && i[S.requestFullscreen]()) : document[S.exitFullscreen]() : o && e.video.el.webkitEnterFullScreen ? (m.on(e.video.el, "webkitbeginfullscreen", function(t) {
				c(t, e)
			}), t ? e.video.el.webkitEnterFullScreen() : e.video.el.webkitExitFullscreen()) : (p.__isFullscreen = t, p.__isFullscreen ? (p.__origOverflow = document.documentElement.style.overflow, document.documentElement.style.overflow = "hidden", m.on(document, "keydown", u)) : (document.documentElement.style.overflow = p.__origOverflow, m.off(document, "keydown", u)), m.toggleClass(document.body, "vcp-full-window", t), b.pub({
				type: g.MSG.FullScreen,
				src: "util",
				detail: {
					isFullscreen: p.__isFullscreen
				}
			}, p.player))
		}

		function h(e) {
			for(var t = arguments.length, i = Array(t > 1 ? t - 1 : 0), o = 1; o < t; o++) i[o - 1] = arguments[o];
			for(var n = 0; n < i.length; n++) {
				var r = i[n];
				for(var s in r) r.hasOwnProperty(s) && (e[s] = e[s] || r[s])
			}
			return e
		}

		function d(e, t) {
			return "undefined" == typeof t ? JSON.parse(localStorage[e] || "null") : void(localStorage[e] = JSON.stringify(t))
		}

		function f(e, t) {
			if(e = e || "0.0.0", t = t || "0.0.0", e == t) return 0;
			for(var i = e.split("."), o = t.split("."), n = Math.max(i.length, o.length), r = 0; r < n; r++) {
				var s = ~~o[r],
					l = ~~i[r];
				if(s < l) return 1;
				if(s > l) return -1
			}
			return -1
		}

		function y(e) {
			return e.replace(/&/g, "&amp;").replace(/</g, "&lt;").replace(/>/g, "&gt;").replace(/\"/g, "&quot;").replace(/\'/g, "&#39;").replace(/\//g, "&#x2F;")
		}
		t.__esModule = !0, t.supportStyle = t.console = t.VideoType = t.CDNPath = t.FullscreenApi = void 0, t.guid = n, t.bind = r, t.isEmpty = s, t.convertTime = l, t.doFullscreen = p, t.extend = h, t.store = d, t.compareVersion = f, t.escapeHTML = y;
		for(var v = i(2), m = o(v), g = i(4), b = o(g), _ = i(1), w = (o(_), 1), S = t.FullscreenApi = {
				requestFullscreen: null,
				exitFullscreen: null,
				fullscreenElement: null,
				fullscreenEnabled: null,
				fullscreenchange: null,
				fullscreenerror: null
			}, E = [
				["requestFullscreen", "exitFullscreen", "fullscreenElement", "fullscreenEnabled", "fullscreenchange", "fullscreenerror"],
				["webkitRequestFullscreen", "webkitExitFullscreen", "webkitFullscreenElement", "webkitFullscreenEnabled", "webkitfullscreenchange", "webkitfullscreenerror"],
				["webkitRequestFullScreen", "webkitCancelFullScreen", "webkitCurrentFullScreenElement", "webkitCancelFullScreen", "webkitfullscreenchange", "webkitfullscreenerror"],
				["mozRequestFullScreen", "mozCancelFullScreen", "mozFullScreenElement", "mozFullScreenEnabled", "mozfullscreenchange", "mozfullscreenerror"],
				["msRequestFullscreen", "msExitFullscreen", "msFullscreenElement", "msFullscreenEnabled", "MSFullscreenChange", "MSFullscreenError"]
			], M = E[0], O = void 0, k = 0; k < E.length; k++) E[k][1] in document && (O = E[k]);
		if(O)
			for(var P = 0; P < O.length; P++) S[M[P]] = O[P];
		t.CDNPath = "//imgcache.qq.com/open/qcloud/video/vcplayer/", t.VideoType = {
			RTMP: "rtmp",
			FLV: "flv",
			M3U8: "m3u8"
		}, t.console = {
			log: function() {
				window.console && window.console.log.apply(window.console, arguments)
			},
			warn: function() {
				window.console && window.console.warn.apply(window.console, arguments)
			},
			error: function() {
				window.console && window.console.error.apply(window.console, arguments)
			}
		}, t.supportStyle = function() {
			var e = document.createElement("div"),
				t = "Khtml O Moz Webkit".split(" "),
				i = t.length;
			return function(o) {
				if(o in e.style) return !0;
				if("-ms-" + o in e.style) return !0;
				for(o = o.replace(/^[a-z]/, function(e) {
						return e.toUpperCase()
					}); i--;)
					if(t[i] + o in e.style) return !0;
				return !1
			}
		}()
	}, function(e, t, i) {
		"use strict";

		function o(e) {
			if(e && e.__esModule) return e;
			var t = {};
			if(null != e)
				for(var i in e) Object.prototype.hasOwnProperty.call(e, i) && (t[i] = e[i]);
			return t["default"] = e, t
		}

		function n(e) {
			var t = e.guid;
			return t ? (h[t] = h[t] || {}, h[t]) : (console.error(e, " has no guid."), {})
		}

		function r(e) {
			var t = e.guid;
			return t ? (d[t] = d[t] || {}, d[t]) : (console.error(e, " has no guid."), {})
		}

		function s(e, t) {
			l(e.type, e, t), l("*", e, t)
		}

		function l(e, t, i) {
			try {
				var o = n(i),
					s = r(i);
				if(!o[e]) return;
				var l = o[e];
				for(var a in l)
					if(l.hasOwnProperty(a)) {
						var c = l[a],
							u = s[a];
						if("function" != typeof u) return !1;
						for(var p = 0; p < c.length; p++) {
							var h = c[p];
							"*" !== h && h !== t.src || u(t)
						}
					}
			} catch(d) {
				window.console && console.error && console.error(d.stack || d)
			}
		}

		function a(e, t, i, o) {
			var s = n(o),
				l = r(o);
			return i.guid ? (l[i.guid] = i, s[e] = s[e] || {}, s[e][i.guid] = s[e][i.guid] || [], s[e][i.guid].push(t), i) : console.error("callback function need guid")
		}

		function c(e, t, i, o) {
			var s = n(o),
				l = r(o);
			if(("*" == e || s[e]) && ("*" == e || s[e][i.guid]))
				for(var a in s)
					if(("*" === e || a == e) && s.hasOwnProperty(a))
						if("*" !== i) {
							var c = s[a][i.guid];
							"*" === t && (c = []);
							for(var u = 0; u < c.length;) c[u] === t ? c.splice(u, 1) : u++;
							0 == c.length && delete s[a][i.guid], p.isEmpty(s[a]) && delete s[a]
						} else {
							for(var h in s[a]) delete l[h];
							delete s[a]
						}
		}
		t.__esModule = !0, t.MSG = void 0, t.pub = s, t.sub = a, t.unsub = c;
		var u = i(3),
			p = o(u),
			h = (t.MSG = {
				Error: "error",
				TimeUpdate: "timeupdate",
				Load: "load",
				MetaLoaded: "loadedmetadata",
				Loaded: "loadeddata",
				Progress: "progress",
				FullScreen: "fullscreen",
				Play: "play",
				Playing: "playing",
				Pause: "pause",
				Ended: "ended",
				Seeking: "seeking",
				Seeked: "seeked",
				Resize: "resize",
				VolumeChange: "volumechange"
			}, {}),
			d = {}
	}, function(e, t, i) {
		"use strict";

		function o(e) {
			return e && e.__esModule ? e : {
				"default": e
			}
		}

		function n(e) {
			if(e && e.__esModule) return e;
			var t = {};
			if(null != e)
				for(var i in e) Object.prototype.hasOwnProperty.call(e, i) && (t[i] = e[i]);
			return t["default"] = e, t
		}

		function r(e, t) {
			if(!(e instanceof t)) throw new TypeError("Cannot call a class as a function")
		}
		t.__esModule = !0, t.Player = t.dom = t.util = t.browser = t.MSG = void 0, i(6);
		var s = i(1),
			l = n(s),
			a = i(2),
			c = n(a),
			u = i(3),
			p = n(u),
			h = i(4),
			d = n(h),
			f = i(10),
			y = o(f),
			v = i(13),
			m = o(v),
			g = i(14),
			b = o(g),
			_ = i(22),
			w = o(_),
			S = i(23),
			E = o(S),
			M = i(24),
			O = o(M),
			k = i(25),
			P = o(k);
		window.console || (window.console = {
			log: function() {},
			error: function() {},
			debug: function() {},
			info: function() {}
		});
		var I = t.MSG = d.MSG,
			T = t.browser = l,
			C = t.util = p,
			x = t.dom = c;
		t.Player = function() {
			function e(t) {
				r(this, e), this.options = t, this.ready = !1;
				var i = t.owner;
				return i ? (this.guid = C.guid(), this.listener = this.options.listener, d.sub("*", "*", C.bind(this, this.handleMsg), this), i = x.get(i), void this.render(i)) : console.error("Player need a container")
			}
			return e.prototype.render = function(e) {
				var t = "vcp-player";
				if(T.TOUCH_ENABLED && (t += " touchable"), this.el = x.createEl("div", {
						"class": t
					}), e.appendChild(this.el), this.errortips = new P["default"](this), this.errortips.render(this.el), this.loading = new O["default"](this), this.loading.render(this.el), this.options.width = this.options.width || e.offsetWidth, this.options.height = this.options.height || e.offsetHeight, this.size(this.options.width, this.options.height), !this.verifyOptions()) return this.listener({
					type: "error",
					code: 5
				}), C.console.error("create failed");
				if(!this.options.flash && T.HASVIDEO) {
					var i = new y["default"](this);
					i.render(this.el), this.video = i
				} else {
					var o = new m["default"](this);
					o.render(this.el), this.video = o
				}
				if(!this.video) return C.console.error("create video failed");
				this.poster = new E["default"](this), this.poster.render(this.el), (T.IS_SAFARI && parseInt(T.SAFARI_VERSION) > 10 || T.IOS_VERSION > 10) && "system" == this.options.controls || (this.bigplay = new w["default"](this), this.bigplay.render(this.el));
				var n = void 0;
				n = !(this.options.controls && "default" != this.options.controls && (!this.options.flash || "system" != this.options.controls)), n && (this.panel = new b["default"](this), this.panel.render(this.el)), this.setup()
			}, e.prototype.verifyOptions = function() {
				return T.IE_VERSION && C.compareVersion(T.IE_VERSION, "8.0") == -1 ? (this.errortips.show({
					code: 5
				}), !1) : T.IS_FILE_PROTOCOL ? (this.errortips.show({
					code: 10
				}), !1) : !!this.options.src || (this.options.videoSource.hasUrl() ? T.IS_IE || !T.IS_ENABLED_FLASH ? this.errortips.show({
					code: 5
				}) : this.errortips.show({
					code: 5
				}) : this.errortips.show({
					code: 12
				}), !1)
			}, e.prototype.size = function(e, t, i) {
				i = i || "cover";
				var o = /^\d+\.?\d{0,2}%$/,
					n = void 0,
					r = void 0;
				if(o.test(e) || o.test(t)) n = e, r = t;
				else {
					var s = this.video ? this.video.videoWidth() : this.options.width,
						l = this.video ? this.video.videoHeight() : this.options.height;
					if(n = e, r = t, s && l) {
						var a = s / l;
						"fit" == i && (n = e, r = n / a, r > t && (n *= t / r, r = t))
					}
					var c = x.getViewportSize();
					c.width > 0 && n > c.width && (n = c.width)
				}
				n += o.test(n) ? "" : "px", r += o.test(r) ? "" : "px", this.el.style.width = n, this.el.style.height = r, this.video && (this.video.width(n), this.video.height(r)), this.width = n, this.height = r
			}, e.prototype.setup = function() {
				if(this.__handleEvent = C.bind(this, this.handleEvent), T.IS_MOBILE) {
					if(this.options.autoplay) {
						var e = this;
						document.addEventListener("WeixinJSBridgeReady", function() {
							e.play()
						})
					}
				} else this.loading.show();
				x.loadScript("//pingjs.qq.com/h5/stats.js?v2.0.4", null, {
					name: "MTAH5",
					sid: "500376528",
					cid: "500383222"
				}, !0)
			}, e.prototype.destroy = function() {
				this.video && this.video.destroy(), this.panel && this.panel.destroy(), this.bigplay && this.bigplay.destroy(), this.loading && this.loading.destroy(), d.unsub("*", "*", this.handleMsg, this), this.video = this.panel = this.bigplay = this.loading = null, this.el.parentNode.removeChild(this.el)
			}, e.prototype.setListener = function(e) {
				this.listener = e
			}, e.prototype.handleEvent = function(e) {
				switch(e.type) {
					case "mousemove":
						if(this.__lastmove && new Date - this.__lastmove < 100) break;
						var t = this;
						if(this.__movecnt = this.__movecnt || 0, this.__movecnt++, this.__movecnt < 5) {
							setTimeout(function() {
								t.__movecnt = 0
							}, 500);
							break
						}
						this.__movecnt = 0, this.__lastmove = +new Date, clearTimeout(this.__moveid), t.panel && t.panel.show(), this.__moveid = setTimeout(function() {
							t.playing() && t.panel && t.panel.hide()
						}, 3e3)
				}
			}, e.prototype.handleMsg = function(e) {
				switch(e.type) {
					case I.Play:
						if(!this.playing()) break;
						x.addClass(this.el, "vcp-playing"), this.video.type() == C.VideoType.RTMP && (this.__wait = !0, this.loading.show()), x.on(this.el, "mousemove", this.__handleEvent);
						break;
					case I.Playing:
						this.loading.hide();
						break;
					case I.TimeUpdate:
						this.__wait && (this.__wait = !1, this.loading.hide());
						break;
					case I.Pause:
						x.off(this.el, "mousemove", this.__handleEvent), x.removeClass(this.el, "vcp-playing");
						break;
					case I.Ended:
						x.off(this.el, "mousemove", this.__handleEvent), this.panel && this.panel.show(), x.removeClass(this.el, "vcp-playing");
						break;
					case I.MetaLoaded:
						this.loading.hide(), this.size(this.options.width, this.options.height);
						break;
					case I.Seeking:
						this.loading.show();
						break;
					case I.Seeked:
						this.loading.hide();
						break;
					case I.FullScreen:
						var t = this;
						setTimeout(function() {
							x.toggleClass(t.el, "vcp-fullscreen", e.detail.isFullscreen)
						}, 0);
						break;
					case I.Error:
						this.loading.hide(), this.errortips.show(e.detail), this.panel && this.panel.show();
						try {
							MtaH5.clickStat("error", {
								error: "true"
							})
						} catch(i) {}
				}!e["private"] && this.listener && this.listener(e)
			}, e.prototype.currentTime = function(e) {
				return this.video.currentTime(e)
			}, e.prototype.duration = function() {
				return this.video.duration()
			}, e.prototype.percent = function(e) {
				return this.video.duration() ? "undefined" == typeof e ? this.video.currentTime() / this.video.duration() : void this.video.currentTime(this.video.duration() * e) : 0
			}, e.prototype.buffered = function() {
				return this.video.duration() ? this.video.buffered() / this.video.duration() : 0
			}, e.prototype.pause = function() {
				this.video.pause()
			}, e.prototype.play = function() {
				var e;
				this.errortips.clear(), (e = this.video).play.apply(e, arguments)
			}, e.prototype.togglePlay = function() {
				this.errortips.clear(), this.video.togglePlay()
			}, e.prototype.stop = function() {
				this.video.stop()
			}, e.prototype.mute = function(e) {
				return this.video.mute(e)
			}, e.prototype.volume = function(e) {
				return this.video.volume(e)
			}, e.prototype.fullscreen = function(e) {
				return this.video.fullscreen(e)
			}, e.prototype.load = function(e, t) {
				this.errortips.clear(), this.loading.show(), this.video.load(e || this.options.src, t)
			}, e.prototype.playing = function() {
				return this.video && this.video.playing()
			}, e.prototype.paused = function() {
				return this.video && this.video.paused()
			}, e
		}()
	}, function(e, t, i) {
		var o = i(7);
		"string" == typeof o && (o = [
			[e.id, o, ""]
		]);
		i(9)(o, {});
		o.locals && (e.exports = o.locals)
	}, function(e, t, i) {
		t = e.exports = i(8)(), t.push([e.id, ".vcp-player{position:relative;z-index:0;font-family:Tahoma,\\\\5FAE\\8F6F\\96C5\\9ED1,\\u5b8b\\u4f53,Verdana,Arial,sans-serif;background-color:#000}.vcp-player video{display:block;overflow:hidden}.vcp-fullscreen.vcp-player,.vcp-fullscreen video,body.vcp-full-window{width:100%!important;height:100%!important}body.vcp-full-window{overflow-y:auto}.vcp-full-window .vcp-player{position:fixed;left:0;top:0;z-index:2147483647}.vcp-pre-flash,.vcp-video{width:100%;height:100%}.vcp-pre-flash{z-index:999;background:#000;position:absolute;top:0;left:0}.vcp-controls-panel{position:absolute;bottom:0;width:100%;font-size:16px;height:3em;z-index:1000}.vcp-controls-panel.show{-webkit-animation:fadeIn ease .8s;animation:fadeIn ease .8s;animation-fill-mode:forwards;-webkit-animation-fill-mode:forwards}.vcp-controls-panel.hide{-webkit-animation:fadeOut ease .8s;animation:fadeOut ease .8s;animation-fill-mode:forwards;-webkit-animation-fill-mode:forwards}.vcp-panel-bg{width:100%;height:100%;position:absolute;left:0;top:0;background-color:#242424;opacity:.8;filter:alpha(opacity=80);z-index:1000}.vcp-playtoggle{cursor:pointer;position:relative;z-index:1001;width:3em;height:100%;float:left;background-image:url(//imgcache.qq.com/open/qcloud/video/vcplayer/img/play_btn.svg);background-image:url(//imgcache.qq.com/open/qcloud/video/vcplayer/img/play_btn.png)\\0}.vcp-playtoggle:focus,.vcp-playtoggle:hover{background-color:#708090;opacity:.9;filter:alpha(opacity=90)}.touchable .vcp-playtoggle:hover{background-color:transparent;opacity:1}.vcp-playing .vcp-playtoggle{background-image:url(//imgcache.qq.com/open/qcloud/video/vcplayer/img/stop_btn.svg);background-image:url(//imgcache.qq.com/open/qcloud/video/vcplayer/img/stop_btn.png)\\0}.vcp-bigplay{width:100%;height:80%;position:absolute;background-color:white\\0;filter:alpha(opacity=0);opacity:0;z-index:1000;top:0;left:0}.vcp-slider{position:relative;z-index:1001;float:left;background:#c4c4c4;height:10px;opacity:.8;filter:alpha(opacity=80);cursor:pointer}.vcp-slider .vcp-slider-track{width:0;height:100%;margin-top:0;opacity:1;filter:alpha(opacity=100);background-color:#1e90ff}.vcp-slider .vcp-slider-thumb{cursor:pointer;background-color:#fff;position:absolute;top:0;left:0;border-radius:1em!important;height:10px;margin-left:-5px;width:10px}.vcp-slider-vertical{position:relative;width:.5em;height:8em;top:-5.6em;z-index:1001;background-color:#1c1c1c;opacity:.9;filter:alpha(opacity=90);cursor:pointer}.vcp-slider-vertical .vcp-slider-track{background-color:#1275cf;width:.5em;height:100%;opacity:.8;filter:alpha(opacity=80)}.vcp-slider-vertical .vcp-slider-thumb{cursor:pointer;position:absolute;background-color:#f0f8ff;width:.8em;height:.8em;border-radius:.8em!important;margin-top:-.4em;top:0;left:-.15em}.vcp-timeline{top:-10px;left:0;height:10px;position:absolute;z-index:1001;width:100%}.vcp-timeline .vcp-slider-thumb{top:-4px}.vcp-timeline .vcp-slider{margin-top:8px;height:2px;width:100%}.vcp-timeline:hover .vcp-slider{margin-top:0;height:10px}.vcp-timeline:hover .vcp-slider-thumb{display:block;width:16px;height:16px;top:-3px;margin-left:-8px}.vcp-timelabel{display:inline-block;line-height:3em;float:left;color:#fff;padding:0 9px}.vcp-timelabel,.vcp-volume{height:3em;z-index:1001;position:relative}.vcp-volume{width:3em;cursor:pointer;float:right;background-color:transparent;opacity:.9;filter:alpha(opacity=90)}.vcp-volume-icon{background-image:url(//imgcache.qq.com/open/qcloud/video/vcplayer/img/volume.svg);background-image:url(//imgcache.qq.com/open/qcloud/video/vcplayer/img/volume.png)\\0;display:inline-block;width:3em;height:3em;position:absolute;left:0;top:0}.vcp-volume-muted .vcp-volume-icon{background-image:url(//imgcache.qq.com/open/qcloud/video/vcplayer/img/muted.svg);background-image:url(//imgcache.qq.com/open/qcloud/video/vcplayer/img/muted.png)\\0}.vcp-volume .vcp-slider-vertical{top:-8.4em;left:1em;display:none}.vcp-volume .vcp-slider-track{position:absolute;bottom:0}.vcp-volume:hover .vcp-slider-vertical{display:block}.vcp-volume .vcp-volume-bg{height:8.8em;width:2em;position:absolute;left:.25em;top:-8.8em;background:#242424;display:none}.vcp-volume:hover .vcp-slider-vertical,.vcp-volume:hover .vcp-volume-bg{display:block}.vcp-fullscreen-toggle{position:relative;width:3em;height:3em;float:right;cursor:pointer;z-index:1001;background-image:url(//imgcache.qq.com/open/qcloud/video/vcplayer/img/fullscreen.svg);background-image:url(//imgcache.qq.com/open/qcloud/video/vcplayer/img/fullscreen.png)\\0}.vcp-fullscreen .vcp-fullscreen-toggle{background-image:url(//imgcache.qq.com/open/qcloud/video/vcplayer/img/fullscreen_exit.svg);background-image:url(//imgcache.qq.com/open/qcloud/video/vcplayer/img/fullscreen_exit.png)\\0}.vcp-loading{left:50%;top:50%;margin-top:-3em}.vcp-loading,.vcp-poster{position:absolute;display:none}.vcp-poster{left:0;top:0;overflow:hidden;z-index:1000;width:100%;height:100%}.vcp-poster-pic{position:relative}.vcp-poster-pic.cover,.vcp-poster-pic.default{left:50%;top:50%;-webkit-transform:translate(-50%,-50%);transform:translate(-50%,-50%)}.vcp-poster-pic.cover{width:100%}.vcp-poster-pic.stretch{width:100%;height:100%}.vcp-error-tips{position:absolute;z-index:1001;width:100%;height:4.5em;left:0;top:50%;color:#ff4500;margin-top:-5.25em;text-align:center;display:none}.vcp-clarityswitcher{height:3em;width:3em;cursor:pointer;position:relative;z-index:1001;float:right;background-color:transparent;opacity:.9}.vcp-vertical-switcher-container{width:3em;position:absolute;left:0;bottom:2.4em;background:#242424;display:none}.vcp-vertical-switcher-current{display:block;color:#fff;text-align:center;line-height:3em}.vcp-vertical-switcher-item{display:block;color:#fff;text-align:center;line-height:2em}.vcp-vertical-switcher-item.current{color:#888}.vcp-share>a{width:3em;height:3em;cursor:pointer;background-image:url(//imgcache.qq.com/open/qcloud/video/vcplayer/img/share_btn.png);opacity:.9;display:block}.vcp-share{width:3em;height:3em;position:relative;float:right;z-index:1001}.vcp-vertical-share-container{width:auto;height:auto;position:absolute;background:rgba(36,36,36,.8);padding:.5em;overflow:hidden;display:none}@-webkit-keyframes fadeOut{0%{opacity:1}to{opacity:0}}@keyframes fadeOut{0%{opacity:1}to{opacity:0}}.fadeOut{-webkit-animation:fadeOut ease .8s;animation:fadeOut ease .8s;animation-fill-mode:forwards;-webkit-animation-fill-mode:forwards}@-webkit-keyframes fadeIn{0%{opacity:0}to{opacity:1}}@keyframes fadeIn{0%{opacity:0}to{opacity:1}}.fadeIn{-webkit-animation:fadeIn ease .8s;animation:fadeIn ease .8s;animation-fill-mode:forwards;-webkit-animation-fill-mode:forwards}", ""])
	}, function(e, t) {
		e.exports = function() {
			var e = [];
			return e.toString = function() {
				for(var e = [], t = 0; t < this.length; t++) {
					var i = this[t];
					i[2] ? e.push("@media " + i[2] + "{" + i[1] + "}") : e.push(i[1])
				}
				return e.join("")
			}, e.i = function(t, i) {
				"string" == typeof t && (t = [
					[null, t, ""]
				]);
				for(var o = {}, n = 0; n < this.length; n++) {
					var r = this[n][0];
					"number" == typeof r && (o[r] = !0)
				}
				for(n = 0; n < t.length; n++) {
					var s = t[n];
					"number" == typeof s[0] && o[s[0]] || (i && !s[2] ? s[2] = i : i && (s[2] = "(" + s[2] + ") and (" + i + ")"), e.push(s))
				}
			}, e
		}
	}, function(e, t, i) {
		function o(e, t) {
			for(var i = 0; i < e.length; i++) {
				var o = e[i],
					n = d[o.id];
				if(n) {
					n.refs++;
					for(var r = 0; r < n.parts.length; r++) n.parts[r](o.parts[r]);
					for(; r < o.parts.length; r++) n.parts.push(c(o.parts[r], t))
				} else {
					for(var s = [], r = 0; r < o.parts.length; r++) s.push(c(o.parts[r], t));
					d[o.id] = {
						id: o.id,
						refs: 1,
						parts: s
					}
				}
			}
		}

		function n(e) {
			for(var t = [], i = {}, o = 0; o < e.length; o++) {
				var n = e[o],
					r = n[0],
					s = n[1],
					l = n[2],
					a = n[3],
					c = {
						css: s,
						media: l,
						sourceMap: a
					};
				i[r] ? i[r].parts.push(c) : t.push(i[r] = {
					id: r,
					parts: [c]
				})
			}
			return t
		}

		function r(e, t) {
			var i = v(),
				o = b[b.length - 1];
			if("top" === e.insertAt) o ? o.nextSibling ? i.insertBefore(t, o.nextSibling) : i.appendChild(t) : i.insertBefore(t, i.firstChild), b.push(t);
			else {
				if("bottom" !== e.insertAt) throw new Error("Invalid value for parameter 'insertAt'. Must be 'top' or 'bottom'.");
				i.appendChild(t)
			}
		}

		function s(e) {
			e.parentNode.removeChild(e);
			var t = b.indexOf(e);
			t >= 0 && b.splice(t, 1)
		}

		function l(e) {
			var t = document.createElement("style");
			return t.type = "text/css", r(e, t), t
		}

		function a(e) {
			var t = document.createElement("link");
			return t.rel = "stylesheet", r(e, t), t
		}

		function c(e, t) {
			var i, o, n;
			if(t.singleton) {
				var r = g++;
				i = m || (m = l(t)), o = u.bind(null, i, r, !1), n = u.bind(null, i, r, !0)
			} else e.sourceMap && "function" == typeof URL && "function" == typeof URL.createObjectURL && "function" == typeof URL.revokeObjectURL && "function" == typeof Blob && "function" == typeof btoa ? (i = a(t), o = h.bind(null, i), n = function() {
				s(i), i.href && URL.revokeObjectURL(i.href)
			}) : (i = l(t), o = p.bind(null, i), n = function() {
				s(i)
			});
			return o(e),
				function(t) {
					if(t) {
						if(t.css === e.css && t.media === e.media && t.sourceMap === e.sourceMap) return;
						o(e = t)
					} else n()
				}
		}

		function u(e, t, i, o) {
			var n = i ? "" : o.css;
			if(e.styleSheet) e.styleSheet.cssText = _(t, n);
			else {
				var r = document.createTextNode(n),
					s = e.childNodes;
				s[t] && e.removeChild(s[t]), s.length ? e.insertBefore(r, s[t]) : e.appendChild(r)
			}
		}

		function p(e, t) {
			var i = t.css,
				o = t.media;
			if(o && e.setAttribute("media", o), e.styleSheet) e.styleSheet.cssText = i;
			else {
				for(; e.firstChild;) e.removeChild(e.firstChild);
				e.appendChild(document.createTextNode(i))
			}
		}

		function h(e, t) {
			var i = t.css,
				o = t.sourceMap;
			o && (i += "\n/*# sourceMappingURL=data:application/json;base64," + btoa(unescape(encodeURIComponent(JSON.stringify(o)))) + " */");
			var n = new Blob([i], {
					type: "text/css"
				}),
				r = e.href;
			e.href = URL.createObjectURL(n), r && URL.revokeObjectURL(r)
		}
		var d = {},
			f = function(e) {
				var t;
				return function() {
					return "undefined" == typeof t && (t = e.apply(this, arguments)), t
				}
			},
			y = f(function() {
				return /msie [6-9]\b/.test(window.navigator.userAgent.toLowerCase())
			}),
			v = f(function() {
				return document.head || document.getElementsByTagName("head")[0]
			}),
			m = null,
			g = 0,
			b = [];
		e.exports = function(e, t) {
			t = t || {}, "undefined" == typeof t.singleton && (t.singleton = y()), "undefined" == typeof t.insertAt && (t.insertAt = "bottom");
			var i = n(e);
			return o(i, t),
				function(e) {
					for(var r = [], s = 0; s < i.length; s++) {
						var l = i[s],
							a = d[l.id];
						a.refs--, r.push(a)
					}
					if(e) {
						var c = n(e);
						o(c, t)
					}
					for(var s = 0; s < r.length; s++) {
						var a = r[s];
						if(0 === a.refs) {
							for(var u = 0; u < a.parts.length; u++) a.parts[u]();
							delete d[a.id]
						}
					}
				}
		};
		var _ = function() {
			var e = [];
			return function(t, i) {
				return e[t] = i, e.filter(Boolean).join("\n")
			}
		}()
	}, function(e, t, i) {
		"use strict";

		function o(e) {
			if(e && e.__esModule) return e;
			var t = {};
			if(null != e)
				for(var i in e) Object.prototype.hasOwnProperty.call(e, i) && (t[i] = e[i]);
			return t["default"] = e, t
		}

		function n(e) {
			return e && e.__esModule ? e : {
				"default": e
			}
		}

		function r(e, t) {
			if(!(e instanceof t)) throw new TypeError("Cannot call a class as a function")
		}

		function s(e, t) {
			if(!e) throw new ReferenceError("this hasn't been initialised - super() hasn't been called");
			return !t || "object" != typeof t && "function" != typeof t ? e : t
		}

		function l(e, t) {
			if("function" != typeof t && null !== t) throw new TypeError("Super expression must either be null or a function, not " + typeof t);
			e.prototype = Object.create(t && t.prototype, {
				constructor: {
					value: e,
					enumerable: !1,
					writable: !0,
					configurable: !0
				}
			}), t && (Object.setPrototypeOf ? Object.setPrototypeOf(e, t) : e.__proto__ = t)
		}
		t.__esModule = !0;
		var a = "function" == typeof Symbol && "symbol" == typeof Symbol.iterator ? function(e) {
				return typeof e
			} : function(e) {
				return e && "function" == typeof Symbol && e.constructor === Symbol && e !== Symbol.prototype ? "symbol" : typeof e
			},
			c = i(11),
			u = n(c),
			p = i(2),
			h = o(p),
			d = i(3),
			f = o(d),
			y = i(4),
			v = i(12),
			m = o(v),
			g = i(1),
			b = o(g),
			_ = (f.FullscreenApi, {
				"0.7.1": "libs/hls.js",
				"0.7min": "libs/hls.min.js",
				"0.8.1": "libs/hls0.8.js",
				"0.8.9": "libs/hls.min.0.8.9.js"
			}),
			w = function(e) {
				function t(i) {
					return r(this, t), s(this, e.call(this, i, "H5Video"))
				}
				return l(t, e), t.prototype.render = function(t) {
					var i, o = this.player.options,
						n = "system" == o.controls ? "" : null,
						r = !!o.autoplay || null;
					return i = o.poster && "object" == a(o.poster) ? o.poster.src : "string" == typeof o.poster ? o.poster : null, this.createEl("video", {
						controls: n,
						preload: o.preload || "auto",
						autoplay: r,
						"webkit-playsinline": "",
						playsinline: "",
						"x-webkit-airplay": "allow",
						"x5-video-player-type": "h5" == o.x5_type ? "h5" : null,
						"x5-video-player-fullscreen": !!o.x5_fullscreen || null,
						"x5-video-orientation": ["landscape", "portrait", "landscape|portrait"][o.x5_orientation] || null,
						"x5-playsinline": 1 == !!o.x5_playsinline ? o.x5_playsinline : null
					}), this.el.style.width = this.player.width, this.el.style.height = this.player.height, e.prototype.render.call(this, t)
				}, t.prototype.__hlsLoaded = function(e) {
					if(!Hls.isSupported()) return this.notify({
						type: "error",
						code: 5,
						timeStamp: +new Date
					});
					this.hls && this.hls.destroy();
					var t = new Hls({
						autoStartLoad: !!this.options.hls_autostartload
					});
					t.loadSource(e), t.attachMedia(this.el), t.on(Hls.Events.MANIFEST_PARSED, function(e, t) {}), t.on(Hls.Events.MEDIA_DETACHED, function() {}), t.on(Hls.Events.ERROR, f.bind(this, this.__hlsOnError)), this.hls = t
				}, t.prototype.__hlsOnManifestParsed = function(e, t) {
					this.metaDataLoaded = !0
				}, t.prototype.__hlsOnError = function(e, t) {
					var i = t.type,
						o = t.details,
						n = t.fatal,
						r = this.hls;
					if(n) switch(i) {
                        case Hls.ErrorTypes.NETWORK_ERROR:
                            o.indexOf("TimeOut") > 0 ? f.console.error("加载视频文件超时") : f.console.error("无法加载视频文件，请检查网络，以及视频文件是否允许跨域请求访问，m3u8文件是否存在 " + (t.response && t.response.status ? "netstatus:" + t.response.status : "")), this.notify({
                                type: "error",
                                code: 2,
                                timeStamp: +new Date
                            }), r.startLoad();
                            break;
                        case Hls.ErrorTypes.MEDIA_ERROR:
                            r.recoverMediaError();
                            break;
                        default:
                            r.destroy()

					}
				}, t.prototype.__flvLoaded = function(e) {
					if(!flvjs.isSupported()) return this.notify({
						type: "error",
						code: 5,
						timeStamp: +new Date
					});
					this.flv && this.flv.destroy();
					var t = flvjs.createPlayer({
						type: "flv",
						isLive: this.player.options.live,
						url: e
					});
					t.attachMediaElement(this.el), t.on(flvjs.Events.ERROR, f.bind(this, function(e, t, i) {
						var o = {
							type: "error"
						};
						e == flvjs.ErrorTypes.NETWORK_ERROR && (o.code = 2), e == flvjs.ErrorTypes.MEDIA_ERROR && (o.code = 1002), e == flvjs.ErrorTypes.OTHER_ERROR, o.timeStamp = +new Date, this.notify(o)
					})), t.on(flvjs.Events.MEDIA_INFO, f.bind(this, function(e, t) {})), t.on(flvjs.Events.STATISTICS_INFO, f.bind(this, function(e, t) {})), this.flv = t, t.load()
				}, t.prototype.setup = function() {
					this.playState = m.PlayStates.IDLE, this.seekState = m.SeekStates.IDLE, this.metaDataLoaded = !1, this.__timebase = +new Date, this.on(y.MSG.MetaLoaded, this.notify), this.on(y.MSG.Loaded, this.notify), this.on(y.MSG.Progress, this.notify), this.on(y.MSG.Play, this.notify), this.on(y.MSG.Playing, this.notify), this.on(y.MSG.Pause, this.notify), this.on(y.MSG.Error, this.notify), this.on(y.MSG.TimeUpdate, this.notify), this.on(y.MSG.Ended, this.notify), this.on(y.MSG.Seeking, this.notify), this.on(y.MSG.Seeked, this.notify), this.on(y.MSG.VolumeChange, this.notify), this.on("durationchange", this.notify), this.load(this.options.src, this.options.m3u8 ? f.VideoType.M3U8 : "")
				}, t.prototype.destroy = function() {
					e.prototype.destroy.call(this), this.hls && this.hls.destroy()
				}, t.prototype.notify = function(e) {
					var t = {
						type: e.type,
						src: this,
						ts: +new Date,
						timeStamp: e.timeStamp
					};
					switch(e.type) {
						case y.MSG.MetaLoaded:
							this.metaDataLoaded = !0;
							break;
						case y.MSG.Error:
							var i = {
								1: "MEDIA_ERR_ABORTED",
								2: "MEDIA_ERR_NETWORK",
								3: "MEDIA_ERR_DECODE",
								4: "MEDIA_ERR_SRC_NOT_SUPPORTED"
							};
							t.detail = this.el && this.el.error || {
								code: e.code
							}, t.detail.reason = i[t.detail.code];
							break;
						case y.MSG.Ended:
							this.pause(), this.playState = m.PlayStates.STOP;
							break;
						case "durationchange":
							0 != this.videoHeight() && (t.type = y.MSG.Resize);
							break;
						case y.MSG.Playing:
							this.playState = e.type.toUpperCase();
							break;
						case y.MSG.Pause:
							this.playState = m.PlayStates.PAUSED;
							break;
						case y.MSG.Seeking:
						case y.MSG.Seeked:
							this.seekState = e.type.toUpperCase()
					}
					"timeupdate" != e.type, this.pub(t)
				}, t.prototype.videoWidth = function() {
					return this.el.videoWidth
				}, t.prototype.videoHeight = function() {
					return this.el.videoHeight
				}, t.prototype.width = function(e) {
					return e ? void(this.el.style.width = e) : this.el.width
				}, t.prototype.height = function(e) {
					return e ? void(this.el.style.height = e) : this.el.height
				}, t.prototype.play = function() {
					this.options.hls_autostartload === !1 && this.hls && this.hls.startLoad(-1), this.el.play()
				}, t.prototype.togglePlay = function() {
					this.paused() ? this.play() : this.pause()
				}, t.prototype.pause = function() {
					this.el.pause()
				}, t.prototype.stop = function() {
					this.el.pause(), this.el.currentTime = 0
				}, t.prototype.paused = function() {
					return this.el.paused
				}, t.prototype.buffered = function() {
					return this.el.buffered.length >= 1 ? this.el.buffered.end(this.el.buffered.length - 1) : 0
				}, t.prototype.currentTime = function(e) {
					return "undefined" == typeof e ? this.el.currentTime : this.el.currentTime = e
				}, t.prototype.duration = function() {
					return this.el.duration || 0
				}, t.prototype.mute = function(e) {
					return "undefined" == typeof e ? this.el.muted : (this.volume(e ? 0 : this.__lastVol), this.el.muted = e)
				}, t.prototype.volume = function(e) {
					return "undefined" == typeof e ? this.el.volume : (e < 0 && (e = 0), e > 1 && (e = 1), 0 != e && (this.__lastVol = e), this.el.muted = 0 == e, this.options.volume = e, this.el.volume = e)
				}, t.prototype.fullscreen = function(e) {
					return f.doFullscreen(this.player, e, this.owner)
				}, t.prototype.load = function(e, t) {
					this.pub({
						type: y.MSG.Load,
						src: this,
						ts: +new Date,
						detail: {
							src: e,
							type: t
						}
					});
					var i = e.indexOf(".m3u8") > -1 || t == f.VideoType.M3U8,
						o = e.indexOf(".flv") > -1;
					if(!b.IS_ENABLED_MSE || !i && !o || b.IS_X5TBS && this.player.options.x5_player || i && b.IS_MAC && b.IS_SAFARI && !b.IS_IOS) this.__type = t, this.el.src = e;
					else {
						var n = this,
							r = _[this.options.hls] || _["0.7.1"];
						i ? (this.__type = f.VideoType.M3U8, "undefined" == typeof window.Hls ? h.loadScript(f.CDNPath + r, function() {
							n.__hlsLoaded.call(n, e)
						}) : this.__hlsLoaded(e)) : o && (this.__type = f.VideoType.FLV, "undefined" == typeof window.flvjs ? h.loadScript(f.CDNPath + "libs/flv.min.js", function() {
							n.__flvLoaded.call(n, e)
						}) : this.__flvLoaded(e))
					}
				}, t.prototype.playing = function() {
					return !this.el.paused
				}, t.prototype.type = function() {
					return this.__type
				}, t
			}(u["default"]);
		t["default"] = w
	}, function(e, t, i) {
		"use strict";

		function o(e) {
			if(e && e.__esModule) return e;
			var t = {};
			if(null != e)
				for(var i in e) Object.prototype.hasOwnProperty.call(e, i) && (t[i] = e[i]);
			return t["default"] = e, t
		}

		function n(e, t) {
			if(!(e instanceof t)) throw new TypeError("Cannot call a class as a function")
		}

		function r(e, t) {
			return t + "_" + e
		}

		function s(e, t) {
			return t.guid && String(t.guid).indexOf("_") == -1 ? e + "_" + t.guid : t.guid
		}
		t.__esModule = !0;
		var l = i(2),
			a = o(l),
			c = i(3),
			u = o(c),
			p = i(4),
			h = o(p),
			d = i(1),
			f = o(d),
			y = function() {
				function e(t, i) {
					n(this, e), this.name = i, this.player = t, this.options = t.options, this.fnCache = {}, this.guid = u.guid()
				}
				return e.prototype.createEl = function(e, t, i) {
					return this.el = a.createEl(e, t, i)
				}, e.prototype.render = function(e) {
					return e && this.el && (this.owner = e, e.appendChild(this.el), this.setup()), this.el
				}, e.prototype.on = function(e, t, i) {
					"string" == typeof e && (i = t, t = e, e = this.el), this.cbs = this.cbs || {};
					var o = s(this.guid, i),
						n = !o,
						l = o && !this.fnCache[o];
					return n || l ? (i = u.bind(this, i, this.guid), this.fnCache[i.guid] = i, o = i.guid) : i = this.fnCache[o], a.on(e, t, i), this.cbs[r(o, t)] = {
						guid: o,
						el: e,
						type: t
					}, i
				}, e.prototype.off = function(e, t, i) {
					"string" == typeof e && (i = t, t = e, e = this.el), f.IS_MOBILE && "click" == t && (t = "touchend");
					var o = s(this.guid, i);
					this.fnCache[o] && (i = this.fnCache[o]), a.off(e, t, i), delete this.cbs[r(o, t)]
				}, e.prototype.pub = function(e) {
					var t = this;
					setTimeout(function() {
						h.pub(e, t.player)
					}, 0)
				}, e.prototype.sub = function(e, t, i) {
					h.sub(e, t, i, this.player)
				}, e.prototype.unsub = function(e, t, i) {
					h.unsub(e, t, i, this.player)
				}, e.prototype.handleMsg = function() {}, e.prototype.setup = function() {}, e.prototype.destroy = function() {
					if(this.handleMsg && this.unsub("*", "*", this.handleMsg), this.cbs) {
						for(var e in this.cbs)
							if(this.cbs.hasOwnProperty(e)) {
								var t = this.cbs[e];
								a.off(t.el, t.type, this.fnCache[t.guid]), delete this.cbs[e]
							}
						this.fnCache = null, this.cbs = null;
						try {
							this.el.parentNode.removeChild(this.el)
						} catch(i) {}
					}
				}, e
			}();
		t["default"] = y
	}, function(e, t) {
		"use strict";
		t.__esModule = !0;
		t.PlayStates = {
			IDLE: "IDLE",
			PLAYING: "PLAYING",
			PAUSED: "PAUSED",
			STOP: "STOP"
		}, t.SeekStates = {
			IDLE: "IDLE",
			SEEKING: "SEEKING",
			SEEKED: "SEEKED"
		}, t.ControlsStates = {
			DEFAULT: "default",
			NONE: "none",
			SYSTEM: ""
		}
	}, function(e, t, i) {
		"use strict";

		function o(e) {
			if(e && e.__esModule) return e;
			var t = {};
			if(null != e)
				for(var i in e) Object.prototype.hasOwnProperty.call(e, i) && (t[i] = e[i]);
			return t["default"] = e, t
		}

		function n(e) {
			return e && e.__esModule ? e : {
				"default": e
			}
		}

		function r(e, t) {
			if(!(e instanceof t)) throw new TypeError("Cannot call a class as a function")
		}

		function s(e, t) {
			if(!e) throw new ReferenceError("this hasn't been initialised - super() hasn't been called");
			return !t || "object" != typeof t && "function" != typeof t ? e : t
		}

		function l(e, t) {
			if("function" != typeof t && null !== t) throw new TypeError("Super expression must either be null or a function, not " + typeof t);
			e.prototype = Object.create(t && t.prototype, {
				constructor: {
					value: e,
					enumerable: !1,
					writable: !0,
					configurable: !0
				}
			}), t && (Object.setPrototypeOf ? Object.setPrototypeOf(e, t) : e.__proto__ = t)
		}

		function a(e) {
			return window.document[e] ? window.document[e] : navigator.appName.indexOf("Microsoft Internet") != -1 ? document.getElementById(e) : document.embeds && document.embeds[e] ? document.embeds[e] : void 0
		}
		t.__esModule = !0;
		var c = i(11),
			u = n(c),
			p = i(4),
			h = i(2),
			d = o(h),
			f = i(3),
			y = o(f),
			v = i(12),
			m = o(v),
			g = i(1),
			b = o(g),
			_ = function(e) {
				function t(i) {
					r(this, t);
					var o = s(this, e.call(this, i, "FlashVideo")),
						n = "vcpFlashCB_" + o.guid;
					return o.__flashCB = n, window[n] || (window[n] = function(e, t) {
						t = t && t[0];
						var i = window[n].fnObj && window[n].fnObj[t.objectID];
						i && i(e, t)
					}, window[n].fnObj = {}), o
				}
				return l(t, e), t.prototype.render = function(e) {
					this.__timebase = +new Date;
					var t = this.player.options,
						i = t.flashUrl || "//imgcache.qq.com/open/qcloud/video/player/release/QCPlayer.swf",
						o = "opaque",
						n = "obj_vcplayer_" + this.player.guid,
						r = this.__flashCB;
					this.__id = n;
					var s = d.createEl("div", {
						"class": "vcp-video"
					});
					s.innerHTML = '\n\t\t<object classid="clsid:d27cdb6e-ae6d-11cf-96b8-444553540000" codebase="" id="' + n + '" width="100%" height="100%">\n            <param name="movie"  value="' + i + '" />\n            <param name="quality" value="autohigh" />\n            <param name="swliveconnect" value="true" />\n            <param name="allowScriptAccess" value="always" />\n            <param name="bgcolor" value="#000" />\n            <param name="allowFullScreen" value="true" />\n            <param name="wmode" value="' + o + '" />\n            <param name="FlashVars" value="cbName=' + r + '" />\n\n            <embed src="' + i + '" width="100%" height="100%" name="' + n + '"\n                   quality="autohigh"\n                   bgcolor="#000"\n                   align="middle" allowFullScreen="true"\n                   allowScriptAccess="always"\n                   type="application/x-shockwave-flash"\n                   swliveconnect="true"\n                   wmode="' + o + '"\n                   FlashVars="cbName=' + r + '"\n                   pluginspage="http://www.macromedia.com/go/getflashplayer" >\n            </embed>\n        </object>\n\t\t', this.container = s, this.owner = e, this.owner.appendChild(s), this.cover = d.createEl("div", {
						"class": "vcp-pre-flash"
					}), this.owner.appendChild(this.cover), window[this.__flashCB].fnObj[this.__id] = y.bind(this, this.notify)
				}, t.prototype.setup = function() {
					this.on("error", this.notify), this.playState = m.PlayStates.IDLE, this.seekState = m.SeekStates.IDLE, this.metaDataLoaded = !1
				}, t.prototype.doPolling = function() {
					this.options.live || (clearInterval(this.__timer), this.__timer = setInterval(this.interval.bind(this), 1e3))
				}, t.prototype.endPolling = function() {
					clearInterval(this.__timer)
				}, t.prototype.interval = function() {
					var e;
					try {
						e = this.el.getState()
					} catch(t) {
						return void this.endPolling()
					}
					if(this.__m3u8) {
						var i = this.currentTime() + e.bufferLength;
						this.__buffered !== i && (this.__buffered = i, this.pub({
							type: p.MSG.Progress,
							src: this,
							ts: +new Date
						})), this.__buffered >= this.duration() && this.endPolling()
					} else this.__rtmp || (this.__bytesloaded != e.bytesLoaded && (this.__bytesloaded = e.bytesLoaded, this.pub({
						type: p.MSG.Progress,
						src: this,
						ts: +new Date
					})), this.__bytesloaded >= this.__bytesTotal && this.endPolling())
				}, t.prototype.destroy = function() {
					delete window[this.__flashCB].fnObj[this.__id], this.endPolling(), e.prototype.destroy.call(this)
				}, t.prototype.notify = function(e, t) {
					var i = {
						type: e,
						ts: +new Date
					};
					try {
						switch(this.options.debug && this.pub({
							type: i.type,
							src: this,
							ts: i.ts,
							detail: y.extend({
								debug: !0
							}, t)
						}), i.type) {
							case "ready":
								if(this.el = a(this.__id), this.setup(), b.IS_FIREFOX) {
									var o = this;
									setTimeout(function() {
										o.el.setAutoPlay(!!o.options.autoplay), o.__timebase = new Date - t.time, o.load(o.options.src)
									}, 0)
								} else this.el.setAutoPlay(!!this.options.autoplay), this.__timebase = new Date - t.time, this.load(this.options.src);
								return;
							case "metaData":
								i.type = p.MSG.MetaLoaded, this.__videoWidth = t.videoWidth, this.__videoHeight = t.videoHeight, this.__duration = t.duration, this.__bytesTotal = t.bytesTotal, this.__prevPlayState = null, this.__m3u8 = t.type === y.VideoType.M3U8, this.__rtmp = t.type === y.VideoType.RTMP, this.__type = t.type, this.__metaloaded = !0, this.metaDataLoaded = !0, this.doPolling();
								var o = this;
								if(!o.cover) break;
								setTimeout(function() {
									o.cover && (o.owner.removeChild(o.cover), o.cover = null)
								}, 500);
								break;
							case "playState":
								this.playState = t.playState, t.playState == m.PlayStates.PLAYING ? (this.__playing = !0, this.__stopped = !1, i.type = p.MSG.Play) : t.playState == m.PlayStates.PAUSED ? (this.__playing = !1, this.__stopped = !1, i.type = p.MSG.Pause) : t.playState == m.PlayStates.STOP ? (this.__playing = !1, this.__stopped = !0, i.type = p.MSG.Ended, this.__prevPlayState = null, this.options.live && (this.metaDataLoaded = !1)) : t.playState == m.PlayStates.IDLE && (this.__playing = !1, this.__stopped = !0, i.type = p.MSG.Ended);
								break;
							case "seekState":
								if(this.seekState = t.seekState, !this.__metaloaded) return;
								if(t.seekState == m.SeekStates.SEEKING) i.type = p.MSG.Seeking;
								else {
									if(t.seekState != m.SeekStates.SEEKED) return;
									this.__m3u8 || this.options.live || t.playState != m.PlayStates.STOP || (this.play(), this.__prevPlayState = t.playState), i.type = p.MSG.Seeked
								}
								break;
							case "netStatus":
								this.options.live || ("NetStream.Buffer.Full" == t.code ? (this.__prevPlayState == m.PlayStates.PAUSED || this.__prevPlayState == m.PlayStates.STOP, this.__prevPlayState = null) : "NetStream.Seek.Complete" == t.code), "NetConnection.Connect.Closed" == t.code && this.options.src.indexOf("rtmp://") > -1 && (this.playState == m.PlayStates.STOP ? (i.type = "error", t = {
									code: 13,
									reason: t.code
								}) : (i.type = "error", t = {
									code: 1002,
									reason: t.code
								})), "NetStream.Play.Stop" == t.code;
								break;
							case "mediaTime":
								this.__videoWidth = t.videoWidth, this.__videoHeight = t.videoHeight, i.type = p.MSG.TimeUpdate;
								break;
							case "error":
								if("NetStream.Seek.InvalidTime" == t.code) return this.currentTime(t.details), !1;
								"NetStream.Play.StreamNotFound" == t.code && this.pub({
									type: "netStatus",
									src: this,
									ts: i.ts,
									detail: t
								});
								var n = isNaN(parseInt(t.code)) ? 1002 : t.code,
									r = isNaN(parseInt(t.code)) ? t.code : t.msg,
									s = r.match(/#(\d+)/);
								s && s[1] && (n = s[1]), t = {
									code: n,
									reason: r || ""
								}, this.metaDataLoaded = !1
						}
						var l = "printLog" == e || "canPlay" == e;
						!l && this.pub({
							type: i.type,
							src: this,
							ts: i.ts,
							detail: t
						})
					} catch(c) {
						y.console.error(e + " " + i.type, c)
					}
				}, t.prototype.handleMsg = function(e) {}, t.prototype.videoWidth = function() {
					return this.__videoWidth
				}, t.prototype.videoHeight = function() {
					return this.__videoHeight
				}, t.prototype.width = function(e) {
					return "undefined" == typeof e ? this.el && this.el.width : (e = "100%", this.el && (this.el.width = e))
				}, t.prototype.height = function(e) {
					return "undefined" == typeof e ? this.el && this.el.height : (e = "100%", this.el && (this.el.height = e))
				}, t.prototype.play = function(e) {
					this.playState == m.PlayStates.PAUSED || this.playState == m.PlayStates.PLAYING || e ? this.el.playerResume() : this.playState != m.PlayStates.PLAYING && this.el.playerPlay()
				}, t.prototype.togglePlay = function() {
					this.metaDataLoaded ? this.playState == m.PlayStates.PAUSED ? this.el.playerResume() : this.playState == m.PlayStates.PLAYING ? this.el.playerPause() : this.playState == m.PlayStates.STOP ? (this.currentTime(0), this.el.playerResume()) : this.el.playerPlay() : this.player.load()
				}, t.prototype.pause = function() {
					this.el.playerPause()
				}, t.prototype.stop = function() {
					this.el.playerStop()
				}, t.prototype.paused = function() {
					return !this.__playing
				}, t.prototype.buffered = function() {
					var e;
					return this.__m3u8 ? this.__buffered || 0 : (e = (this.__bytesloaded || 0) / (this.__bytesTotal || 1), this.duration() * e)
				}, t.prototype.currentTime = function(e) {
					return "undefined" == typeof e ? this.el.getPosition() : void this.el.playerSeek(e)
				}, t.prototype.duration = function() {
					return this.__duration
				}, t.prototype.mute = function(e) {
					return "undefined" == typeof e ? 0 == this.volume() : void this.volume(e ? 0 : this.__lastVol)
				}, t.prototype.volume = function(e) {
					return "undefined" == typeof e ? this.el && this.el.getState().volume : (this.el && this.el.playerVolume(e), 0 != e && (this.__lastVol = e), this.options.volume = e, void this.pub({
						type: p.MSG.VolumeChange,
						src: this,
						ts: +new Date
					}))
				}, t.prototype.fullscreen = function(e) {
					return y.doFullscreen(this.player, e, this.owner)
				}, t.prototype.load = function(e, t) {
					this.pub({
						type: p.MSG.Load,
						src: this,
						ts: +new Date,
						detail: {
							src: e,
							type: t
						}
					}), this.el && this.el.playerLoad(e)
				}, t.prototype.playing = function() {
					return this.el && this.el.getState && this.el.getState().playState === m.PlayStates.PLAYING
				}, t.prototype.type = function() {
					return this.__type
				}, t.prototype.state = function() {
					return this.playState
				}, t
			}(u["default"]);
		t["default"] = _
	}, function(e, t, i) {
		"use strict";

		function o(e) {
			if(e && e.__esModule) return e;
			var t = {};
			if(null != e)
				for(var i in e) Object.prototype.hasOwnProperty.call(e, i) && (t[i] = e[i]);
			return t["default"] = e, t
		}

		function n(e) {
			return e && e.__esModule ? e : {
				"default": e
			}
		}

		function r(e, t) {
			if(!(e instanceof t)) throw new TypeError("Cannot call a class as a function")
		}

		function s(e, t) {
			if(!e) throw new ReferenceError("this hasn't been initialised - super() hasn't been called");
			return !t || "object" != typeof t && "function" != typeof t ? e : t
		}

		function l(e, t) {
			if("function" != typeof t && null !== t) throw new TypeError("Super expression must either be null or a function, not " + typeof t);
			e.prototype = Object.create(t && t.prototype, {
				constructor: {
					value: e,
					enumerable: !1,
					writable: !0,
					configurable: !0
				}
			}), t && (Object.setPrototypeOf ? Object.setPrototypeOf(e, t) : e.__proto__ = t)
		}
		t.__esModule = !0;
		var a = i(11),
			c = n(a),
			u = i(15),
			p = n(u),
			h = i(16),
			d = n(h),
			f = i(17),
			y = i(18),
			v = n(y),
			m = i(19),
			g = n(m),
			b = i(20),
			_ = n(b),
			w = i(21),
			S = n(w),
			E = i(4),
			M = i(2),
			O = o(M),
			k = i(3),
			P = o(k),
			I = i(1),
			T = o(I),
			C = function(e) {
				function t(i) {
					return r(this, t), s(this, e.call(this, i, "Panel"))
				}
				return l(t, e), t.prototype.render = function(t) {
					return this.createEl("div", {
						"class": "vcp-controls-panel"
					}), this.el.appendChild(O.createEl("div", {
						"class": "vcp-panel-bg"
					})), this.playToggle = new p["default"](this.player), this.playToggle.render(this.el), this.timelabel = new g["default"](this.player), this.timelabel.render(this.el), this.timeline = new v["default"](this.player), this.timeline.render(this.el), this.options.fullscreenEnabled === !0 && (this.fullscreen = new d["default"](this.player), this.fullscreen.render(this.el)), T.IS_MOBILE || (this.volume = new _["default"](this.player), this.volume.render(this.el)), this.options.videoSource && this.options.videoSource.definitions.length > 1 && !T.IS_MOBILE && (this.claritySwitcher = new S["default"](this.player), this.claritySwitcher.render(this.el)), e.prototype.render.call(this, t)
				}, t.prototype.setup = function() {
					var e = P.bind(this, this.handleMsg);
					this.sub(f.MSG.Changing, this.volume, e), this.sub(f.MSG.Changed, this.timeline.progress, e), this.sub(E.MSG.TimeUpdate, this.player.video, e), this.sub(E.MSG.Progress, this.player.video, e), this.sub(E.MSG.MetaLoaded, this.player.video, e), this.sub(E.MSG.Pause, this.player.video, e), this.sub(E.MSG.Play, this.player.video, e), this.sub(E.MSG.Ended, this.player.video, e)
				}, t.prototype.handleMsg = function(e) {
					switch(e.type) {
						case E.MSG.MetaLoaded:
							this.timeline.percent(this.player.percent()), this.timeline.buffered(this.player.buffered()), this.player.volume("undefined" == typeof this.options.volume ? .5 : this.options.volume), !this.options.autoplay && this.show();
							break;
						case E.MSG.TimeUpdate:
							this.timeline.scrubbing || this.timeline.percent(this.player.percent());
							break;
						case E.MSG.Pause:
							this.show();
							break;
						case E.MSG.Play:
							this.hide();
							break;
						case E.MSG.Progress:
							this.timeline.buffered(this.player.buffered());
							break;
						case f.MSG.Changed:
							e.src === this.timeline.progress && this.player.percent(this.timeline.percent());
							break;
						case E.MSG.Ended:
							this.show()
					}
				}, t.prototype.toggle = function() {
					O.hasClass(this.el, "show") ? this.hide() : this.show()
				}, t.prototype.show = function() {
					O.hasClass(this.el, "hide") && (O.removeClass(this.el, "hide"), O.addClass(this.el, "show"))
				}, t.prototype.hide = function() {
					O.removeClass(this.el, "show"), O.addClass(this.el, "hide")
				}, t
			}(c["default"]);
		t["default"] = C
	}, function(e, t, i) {
		"use strict";

		function o(e) {
			if(e && e.__esModule) return e;
			var t = {};
			if(null != e)
				for(var i in e) Object.prototype.hasOwnProperty.call(e, i) && (t[i] = e[i]);
			return t["default"] = e, t
		}

		function n(e) {
			return e && e.__esModule ? e : {
				"default": e
			}
		}

		function r(e, t) {
			if(!(e instanceof t)) throw new TypeError("Cannot call a class as a function")
		}

		function s(e, t) {
			if(!e) throw new ReferenceError("this hasn't been initialised - super() hasn't been called");
			return !t || "object" != typeof t && "function" != typeof t ? e : t
		}

		function l(e, t) {
			if("function" != typeof t && null !== t) throw new TypeError("Super expression must either be null or a function, not " + typeof t);
			e.prototype = Object.create(t && t.prototype, {
				constructor: {
					value: e,
					enumerable: !1,
					writable: !0,
					configurable: !0
				}
			}), t && (Object.setPrototypeOf ? Object.setPrototypeOf(e, t) : e.__proto__ = t)
		}
		t.__esModule = !0;
		var a = i(11),
			c = n(a),
			u = i(2),
			p = (o(u), i(4)),
			h = (o(p), i(3)),
			d = (o(h), i(12)),
			f = (o(d), function(e) {
				function t(i) {
					return r(this, t), s(this, e.call(this, i, "PlayToggle"))
				}
				return l(t, e), t.prototype.render = function(t) {
					return this.createEl("div", {
						"class": "vcp-playtoggle"
					}), e.prototype.render.call(this, t)
				}, t.prototype.setup = function() {
					this.on("click", this.onClick)
				}, t.prototype.onClick = function() {
					this.player.togglePlay()
				}, t.prototype.handleMsg = function(e) {
					console.log("@" + this.name, e)
				}, t
			}(c["default"]));
		t["default"] = f
	}, function(e, t, i) {
		"use strict";

		function o(e) {
			if(e && e.__esModule) return e;
			var t = {};
			if(null != e)
				for(var i in e) Object.prototype.hasOwnProperty.call(e, i) && (t[i] = e[i]);
			return t["default"] = e, t
		}

		function n(e) {
			return e && e.__esModule ? e : {
				"default": e
			}
		}

		function r(e, t) {
			if(!(e instanceof t)) throw new TypeError("Cannot call a class as a function")
		}

		function s(e, t) {
			if(!e) throw new ReferenceError("this hasn't been initialised - super() hasn't been called");
			return !t || "object" != typeof t && "function" != typeof t ? e : t
		}

		function l(e, t) {
			if("function" != typeof t && null !== t) throw new TypeError("Super expression must either be null or a function, not " + typeof t);
			e.prototype = Object.create(t && t.prototype, {
				constructor: {
					value: e,
					enumerable: !1,
					writable: !0,
					configurable: !0
				}
			}), t && (Object.setPrototypeOf ? Object.setPrototypeOf(e, t) : e.__proto__ = t)
		}
		t.__esModule = !0;
		var a = i(11),
			c = n(a),
			u = i(2),
			p = (o(u), i(4)),
			h = (o(p), i(3)),
			d = o(h),
			f = function(e) {
				function t(i) {
					return r(this, t), s(this, e.call(this, i, "FullscreenToggle"))
				}
				return l(t, e), t.prototype.render = function(t) {
					return this.createEl("div", {
						"class": "vcp-fullscreen-toggle"
					}), window.fsApi = d.FullscreenApi, e.prototype.render.call(this, t)
				}, t.prototype.setup = function() {
					this.on("click", this.onClick)
				}, t.prototype.onClick = function() {
					this.player.fullscreen(!this.player.fullscreen())
				}, t.prototype.handleMsg = function(e) {
					console.log(t.name, e)
				}, t
			}(c["default"]);
		t["default"] = f
	}, function(e, t, i) {
		"use strict";

		function o(e) {
			if(e && e.__esModule) return e;
			var t = {};
			if(null != e)
				for(var i in e) Object.prototype.hasOwnProperty.call(e, i) && (t[i] = e[i]);
			return t["default"] = e, t
		}

		function n(e) {
			return e && e.__esModule ? e : {
				"default": e
			}
		}

		function r(e, t) {
			if(!(e instanceof t)) throw new TypeError("Cannot call a class as a function")
		}

		function s(e, t) {
			if(!e) throw new ReferenceError("this hasn't been initialised - super() hasn't been called");
			return !t || "object" != typeof t && "function" != typeof t ? e : t
		}

		function l(e, t) {
			if("function" != typeof t && null !== t) throw new TypeError("Super expression must either be null or a function, not " + typeof t);
			e.prototype = Object.create(t && t.prototype, {
				constructor: {
					value: e,
					enumerable: !1,
					writable: !0,
					configurable: !0
				}
			}), t && (Object.setPrototypeOf ? Object.setPrototypeOf(e, t) : e.__proto__ = t)
		}
		t.__esModule = !0, t.MSG = void 0;
		var a = i(11),
			c = n(a),
			u = i(2),
			p = o(u),
			h = i(4),
			d = (o(h), i(3)),
			f = (o(d), t.MSG = {
				Changing: "sliderchanging",
				Changed: "sliderchanged"
			}),
			y = function(e) {
				function t(i, o) {
					r(this, t);
					var n = s(this, e.call(this, i, "Slider"));
					return n.vertical = o || !1, n
				}
				return l(t, e), t.prototype.render = function(t, i) {
					var o = this.vertical ? "vcp-slider-vertical" : "vcp-slider";
					return this.createEl("div", {
						"class": o
					}), this.track = p.createEl("div", {
						"class": "vcp-slider-track"
					}), this.thumb = p.createEl("div", {
						"class": "vcp-slider-thumb"
					}), this.el.appendChild(this.track), this.el.appendChild(this.thumb), this.enabled = "undefined" == typeof i || i, e.prototype.render.call(this, t)
				}, t.prototype.setup = function() {
					this.enabled && (this.ownerDoc = document.body.ownerDocument, this.on("mousedown", this.mousedown), this.on("touchstart", this.mousedown))
				}, t.prototype.handleMsg = function(e) {}, t.prototype.mousedown = function(e) {
					return e.preventDefault && e.preventDefault(), this.pos = p.findElPosition(this.el), this.on(this.ownerDoc, "mouseup", this.mouseup), this.on(this.ownerDoc, "mousemove", this.mousemove), this.on(this.ownerDoc, "touchend", this.mouseup), this.on(this.ownerDoc, "touchmove", this.mousemove), this.mousemove(e), !1
				}, t.prototype.mouseup = function(e) {
					e.target || e.srcElement;
					this.off(this.ownerDoc, "mouseup", this.mouseup), this.off(this.ownerDoc, "mousemove", this.mousemove), this.off(this.ownerDoc, "touchend", this.mouseup), this.off(this.ownerDoc, "touchmove", this.mousemove), this.pub({
						type: f.Changed,
						src: this,
						"private": !0
					})
				}, t.prototype.mousemove = function(e) {
					var t = p.getPointerPosition(this.el, e, this.pos);
					this.vertical ? (this.__percent = 1 - t.y, this.thumb.style.top = 100 * this.__percent + "%") : (this.__percent = t.x, this.thumb.style.left = 100 * this.__percent + "%"), this.__percent = Number(this.__percent.toFixed(3)), this.pub({
						type: f.Changing,
						src: this,
						"private": !0
					})
				}, t.prototype.percent = function(e) {
					return e || 0 == e ? (this.__percent = e, void(this.vertical ? this.thumb.style.top = 100 * this.__percent + "%" : this.thumb.style.left = 100 * this.__percent + "%")) : this.__percent
				}, t
			}(c["default"]);
		t["default"] = y
	}, function(e, t, i) {
		"use strict";

		function o(e) {
			if(e && e.__esModule) return e;
			var t = {};
			if(null != e)
				for(var i in e) Object.prototype.hasOwnProperty.call(e, i) && (t[i] = e[i]);
			return t["default"] = e, t
		}

		function n(e) {
			return e && e.__esModule ? e : {
				"default": e
			}
		}

		function r(e, t) {
			if(!(e instanceof t)) throw new TypeError("Cannot call a class as a function")
		}

		function s(e, t) {
			if(!e) throw new ReferenceError("this hasn't been initialised - super() hasn't been called");
			return !t || "object" != typeof t && "function" != typeof t ? e : t
		}

		function l(e, t) {
			if("function" != typeof t && null !== t) throw new TypeError("Super expression must either be null or a function, not " + typeof t);
			e.prototype = Object.create(t && t.prototype, {
				constructor: {
					value: e,
					enumerable: !1,
					writable: !0,
					configurable: !0
				}
			}), t && (Object.setPrototypeOf ? Object.setPrototypeOf(e, t) : e.__proto__ = t)
		}
		t.__esModule = !0;
		var a = i(17),
			c = n(a),
			u = i(11),
			p = n(u),
			h = i(2),
			d = (o(h), i(3)),
			f = o(d),
			y = function(e) {
				function t(i) {
					return r(this, t), s(this, e.call(this, i, "Timeline"))
				}
				return l(t, e), t.prototype.render = function(t) {
					return this.enabled = !this.options.live, this.createEl("div", {
						"class": "vcp-timeline"
					}), this.progress = new c["default"](this.player, (!1)), this.progress.render(this.el, this.enabled), this.track = this.progress.track, this.enabled || (this.el.style.display = "none"), e.prototype.render.call(this, t)
				}, t.prototype.setup = function() {
					this.enabled && (this.sub(a.MSG.Changing, this.progress, f.bind(this, this.handleMsg)), this.sub(a.MSG.Changed, this.progress, f.bind(this, this.handleMsg)))
				}, t.prototype.handleMsg = function(e) {
					e.type === a.MSG.Changing ? (this.scrubbing = !0, this.syncLabel(this.percent())) : e.type === a.MSG.Changed && (this.scrubbing = !1)
				}, t.prototype.syncLabel = function(e) {
					var t = this.player.duration();
					e = Math.min(e, 1);
					var i = "";
					t && (i = f.convertTime(e * t) + " / " + f.convertTime(t)), this.pub({
						type: "timelabel",
						src: "timeline",
						label: i,
						"private": !0
					})
				}, t.prototype.buffered = function(e) {
					this.enabled && (e = Math.min(e, 1), this.__buffered = e, this.track.style.width = 100 * e + "%")
				}, t.prototype.percent = function(e) {
					if(this.enabled) return "undefined" == typeof e ? this.progress.percent() || 0 : (e = Math.min(e, 1), this.syncLabel(e), this.__buffered < e && this.buffered(this.player.buffered()), this.progress.percent(e))
				}, t
			}(p["default"]);
		t["default"] = y
	}, function(e, t, i) {
		"use strict";

		function o(e) {
			if(e && e.__esModule) return e;
			var t = {};
			if(null != e)
				for(var i in e) Object.prototype.hasOwnProperty.call(e, i) && (t[i] = e[i]);
			return t["default"] = e, t
		}

		function n(e) {
			return e && e.__esModule ? e : {
				"default": e
			}
		}

		function r(e, t) {
			if(!(e instanceof t)) throw new TypeError("Cannot call a class as a function")
		}

		function s(e, t) {
			if(!e) throw new ReferenceError("this hasn't been initialised - super() hasn't been called");
			return !t || "object" != typeof t && "function" != typeof t ? e : t
		}

		function l(e, t) {
			if("function" != typeof t && null !== t) throw new TypeError("Super expression must either be null or a function, not " + typeof t);
			e.prototype = Object.create(t && t.prototype, {
				constructor: {
					value: e,
					enumerable: !1,
					writable: !0,
					configurable: !0
				}
			}), t && (Object.setPrototypeOf ? Object.setPrototypeOf(e, t) : e.__proto__ = t)
		}
		t.__esModule = !0;
		var a = i(17),
			c = (n(a), i(11)),
			u = n(c),
			p = i(2),
			h = (o(p), i(3)),
			d = o(h),
			f = function(e) {
				function t(i) {
					return r(this, t), s(this, e.call(this, i, "Timelabel"))
				}
				return l(t, e), t.prototype.render = function(t) {
					return this.createEl("span", {
						"class": "vcp-timelabel"
					}), e.prototype.render.call(this, t)
				}, t.prototype.setup = function() {
					this.sub("timelabel", "timeline", d.bind(this, this.handleMsg))
				}, t.prototype.handleMsg = function(e) {
					this.el.innerHTML = e.label
				}, t
			}(u["default"]);
		t["default"] = f
	}, function(e, t, i) {
		"use strict";

		function o(e) {
			if(e && e.__esModule) return e;
			var t = {};
			if(null != e)
				for(var i in e) Object.prototype.hasOwnProperty.call(e, i) && (t[i] = e[i]);
			return t["default"] = e, t
		}

		function n(e) {
			return e && e.__esModule ? e : {
				"default": e
			}
		}

		function r(e, t) {
			if(!(e instanceof t)) throw new TypeError("Cannot call a class as a function")
		}

		function s(e, t) {
			if(!e) throw new ReferenceError("this hasn't been initialised - super() hasn't been called");
			return !t || "object" != typeof t && "function" != typeof t ? e : t
		}

		function l(e, t) {
			if("function" != typeof t && null !== t) throw new TypeError("Super expression must either be null or a function, not " + typeof t);
			e.prototype = Object.create(t && t.prototype, {
				constructor: {
					value: e,
					enumerable: !1,
					writable: !0,
					configurable: !0
				}
			}), t && (Object.setPrototypeOf ? Object.setPrototypeOf(e, t) : e.__proto__ = t)
		}
		t.__esModule = !0;
		var a = i(17),
			c = n(a),
			u = i(11),
			p = n(u),
			h = i(2),
			d = o(h),
			f = i(3),
			y = o(f),
			v = i(4),
			m = function(e) {
				function t(i) {
					return r(this, t), s(this, e.call(this, i, "Volume"))
				}
				return l(t, e), t.prototype.render = function(t) {
					return this.createEl("div", {
						"class": "vcp-volume"
					}), this.bg = d.createEl("div", {
						"class": "vcp-volume-bg"
					}), this.el.appendChild(this.bg), this.volume = new c["default"](this.player, (!0)), this.volume.render(this.el), this.track = this.volume.track, this.icon = d.createEl("span", {
						"class": "vcp-volume-icon"
					}), this.el.appendChild(this.icon), e.prototype.render.call(this, t)
				}, t.prototype.setup = function() {
					this.sub(a.MSG.Changing, this.volume, y.bind(this, this.handleMsg)), this.sub(a.MSG.Changed, this.volume, y.bind(this, this.handleMsg)), this.sub(v.MSG.VolumeChange, this.player.video, y.bind(this, this.handleMsg)), this.on(this.icon, "click", this.toggleMute)
				}, t.prototype.handleMsg = function(e) {
					switch(e.type) {
						case a.MSG.Changing:
							this.syncTrack(this.percent());
							break;
						case a.MSG.Changed:
							this.percent(this.percent());
							break;
						case v.MSG.VolumeChange:
							var t = this.player.volume();
							this.syncTrack(t), 0 == t ? this.syncMute(!0) : t > 0 && this.__muted && this.syncMute(!1)
					}
				}, t.prototype.toggleMute = function(e) {
					var t = !this.player.mute();
					this.player.mute(t)
				}, t.prototype.syncMute = function(e) {
					e ? d.addClass(this.el, "vcp-volume-muted") : d.removeClass(this.el, "vcp-volume-muted"), this.__muted = e
				}, t.prototype.syncTrack = function(e) {
					this.track.style.height = 100 * e + "%", this.volume.percent(1 - e)
				}, t.prototype.percent = function(e) {
					return "undefined" == typeof e ? 1 - this.volume.percent() || 0 : (this.player.volume(e), e)
				}, t
			}(p["default"]);
		t["default"] = m
	}, function(e, t, i) {
		"use strict";

		function o(e) {
			if(e && e.__esModule) return e;
			var t = {};
			if(null != e)
				for(var i in e) Object.prototype.hasOwnProperty.call(e, i) && (t[i] = e[i]);
			return t["default"] = e, t
		}

		function n(e) {
			return e && e.__esModule ? e : {
				"default": e
			}
		}

		function r(e, t) {
			if(!(e instanceof t)) throw new TypeError("Cannot call a class as a function")
		}

		function s(e, t) {
			if(!e) throw new ReferenceError("this hasn't been initialised - super() hasn't been called");
			return !t || "object" != typeof t && "function" != typeof t ? e : t
		}

		function l(e, t) {
			if("function" != typeof t && null !== t) throw new TypeError("Super expression must either be null or a function, not " + typeof t);
			e.prototype = Object.create(t && t.prototype, {
				constructor: {
					value: e,
					enumerable: !1,
					writable: !0,
					configurable: !0
				}
			}), t && (Object.setPrototypeOf ? Object.setPrototypeOf(e, t) : e.__proto__ = t)
		}
		t.__esModule = !0;
		var a = i(11),
			c = n(a),
			u = i(2),
			p = o(u),
			h = i(3),
			d = o(h),
			f = {
                od: "超清",
                hd: "高清",
                sd: "标清"
			},
			y = function(e) {
				function t(i) {
					r(this, t);
					var o = s(this, e.call(this, i, "ClaritySwitcher"));
					return f = d.extend({}, i.options.clarityLabel, f), i.claritySwitcher = o, o
				}
				return l(t, e), t.prototype.render = function(t) {
					this.show = !1, this.createEl("div", {
						"class": "vcp-clarityswitcher"
					}), this.current = p.createEl("a", {
						"class": "vcp-vertical-switcher-current"
					}), this.container = p.createEl("div", {
						"class": "vcp-vertical-switcher-container"
					}), this.items = [], this.currentItem = "";
					var i = this.options.videoSource;
					this.current.innerHTML = f[i.curDef], this.el.appendChild(this.current);
					for(var o = 0; o < i.definitions.length; o++) {
						var n = p.createEl("a", {
							"class": "vcp-vertical-switcher-item"
						});
						n.innerHTML = f[i.definitions[o]], i.definitions[o] == i.curDef && (p.addClass(n, "current"), this.currentItem = n), n.setAttribute("data-def", i.definitions[o]), this.items.push(n), this.container.appendChild(n)
					}
					return this.el.appendChild(this.container), e.prototype.render.call(this, t)
				}, t.prototype.setup = function() {
					this.on("click", this.onClick), this.on("mouseenter", this.onMouseEnter), this.on("mouseleave", this.onMouseLeave)
				}, t.prototype.onClick = function(e) {
					var t = e.target.getAttribute("data-def");
					t ? (this.current.innerHTML = f[t], p.removeClass(this.currentItem, "current"), p.addClass(e.target, "current"), this.currentItem = e.target, this.player._switchClarity(t)) : !this.show
				}, t.prototype.onMouseLeave = function() {
					this.container.style.display = "none", this.show = !1
				}, t.prototype.onMouseEnter = function() {
					this.container.style.display = "block", this.show = !0
				}, t.prototype.setClarity = function(e) {
					e && (this.current.innerHTML = f[e], p.removeClass(document.querySelector(".vcp-vertical-switcher-item.current"), "current"), p.addClass(document.querySelector('.vcp-vertical-switcher-item[data-def="' + e + '"]'), "current"), this.currentItem = document.querySelector('.vcp-vertical-switcher-item[data-def="' + e + '"]'), this.player._switchClarity(e))
				}, t
			}(c["default"]);
		t["default"] = y
	}, function(e, t, i) {
		"use strict";

		function o(e) {
			if(e && e.__esModule) return e;
			var t = {};
			if(null != e)
				for(var i in e) Object.prototype.hasOwnProperty.call(e, i) && (t[i] = e[i]);
			return t["default"] = e, t
		}

		function n(e) {
			return e && e.__esModule ? e : {
				"default": e
			}
		}

		function r(e, t) {
			if(!(e instanceof t)) throw new TypeError("Cannot call a class as a function")
		}

		function s(e, t) {
			if(!e) throw new ReferenceError("this hasn't been initialised - super() hasn't been called");
			return !t || "object" != typeof t && "function" != typeof t ? e : t
		}

		function l(e, t) {
			if("function" != typeof t && null !== t) throw new TypeError("Super expression must either be null or a function, not " + typeof t);
			e.prototype = Object.create(t && t.prototype, {
				constructor: {
					value: e,
					enumerable: !1,
					writable: !0,
					configurable: !0
				}
			}), t && (Object.setPrototypeOf ? Object.setPrototypeOf(e, t) : e.__proto__ = t)
		}
		t.__esModule = !0;
		var a = i(11),
			c = n(a),
			u = i(1),
			p = o(u),
			h = function(e) {
				function t(i) {
					return r(this, t), s(this, e.call(this, i, "BigPlay"))
				}
				return l(t, e), t.prototype.render = function(t) {
					return this.createEl("div", {
						"class": "vcp-bigplay"
					}), e.prototype.render.call(this, t)
				}, t.prototype.setup = function() {
					this.on("click", this.onClick)
				}, t.prototype.onClick = function() {
					var e = this.player.video;
					return p.IS_MOBILE && !e.paused() ? this.player.panel && this.player.panel.toggle() : void this.player.togglePlay()
				}, t.prototype.handleMsg = function(e) {
					console.log("@" + this.name, e)
				}, t
			}(c["default"]);
		t["default"] = h
	}, function(e, t, i) {
		"use strict";

		function o(e) {
			if(e && e.__esModule) return e;
			var t = {};
			if(null != e)
				for(var i in e) Object.prototype.hasOwnProperty.call(e, i) && (t[i] = e[i]);
			return t["default"] = e, t
		}

		function n(e) {
			return e && e.__esModule ? e : {
				"default": e
			}
		}

		function r(e, t) {
			if(!(e instanceof t)) throw new TypeError("Cannot call a class as a function")
		}

		function s(e, t) {
			if(!e) throw new ReferenceError("this hasn't been initialised - super() hasn't been called");
			return !t || "object" != typeof t && "function" != typeof t ? e : t
		}

		function l(e, t) {
			if("function" != typeof t && null !== t) throw new TypeError("Super expression must either be null or a function, not " + typeof t);
			e.prototype = Object.create(t && t.prototype, {
				constructor: {
					value: e,
					enumerable: !1,
					writable: !0,
					configurable: !0
				}
			}), t && (Object.setPrototypeOf ? Object.setPrototypeOf(e, t) : e.__proto__ = t)
		}
		t.__esModule = !0;
		var a = "function" == typeof Symbol && "symbol" == typeof Symbol.iterator ? function(e) {
				return typeof e
			} : function(e) {
				return e && "function" == typeof Symbol && e.constructor === Symbol && e !== Symbol.prototype ? "symbol" : typeof e
			},
			c = i(11),
			u = n(c),
			p = i(2),
			h = o(p),
			d = i(3),
			f = o(d),
			y = i(1),
			v = o(y),
			m = i(4),
			g = function(e) {
				function t(i) {
					r(this, t);
					var o = s(this, e.call(this, i, "Poster"));
					return o.options.poster && "object" == a(o.options.poster) ? o.poster = o.options.poster : "string" == typeof o.options.poster ? o.poster = {
						src: o.options.poster
					} : o.poster = {}, o
				}
				return l(t, e), t.prototype.render = function(t) {
					this.createEl("div", {
						"class": "vcp-poster"
					}), this.hide();
					var i = this.poster;
					if(i) {
						this.pic = h.createEl("img", {
							"class": "vcp-poster-pic"
						});
						var o = this.poster.style;
						switch(o) {
							case "stretch":
								h.addClass(this.pic, "stretch");
								break;
							case "cover":
								h.addClass(this.pic, "cover");
								break;
							default:
								h.addClass(this.pic, "default")
						}
						this.el.appendChild(this.pic)
					}
					return e.prototype.render.call(this, t)
				}, t.prototype.setup = function() {
					this.on("click", this.onClick), this.sub(m.MSG.Load, this.player.video, f.bind(this, this.handleMsg)), this.sub(m.MSG.MetaLoaded, this.player.video, f.bind(this, this.handleMsg)), this.sub(m.MSG.Play, this.player.video, f.bind(this, this.handleMsg)), this.sub(m.MSG.Pause, this.player.video, f.bind(this, this.handleMsg)), this.sub(m.MSG.Ended, this.player.video, f.bind(this, this.handleMsg)), this.sub(m.MSG.Error, this.player.video, f.bind(this, this.handleMsg))
				}, t.prototype.onClick = function() {
					this.pub({
						type: "click",
						src: this
					}), (v.IS_SAFARI && parseInt(v.SAFARI_VERSION) > 10 || v.IOS_VERSION > 10) && "system" == this.player.options.controls && this.player.togglePlay()
				}, t.prototype.handleMsg = function(e) {
					switch(e.type) {
						case m.MSG.Load:
							this.__loaded = !1, this.setPoster(this.poster.start);
							break;
						case m.MSG.MetaLoaded:
							if(this.__loaded = !0, !this.player.playing()) break;
							this.hide();
						case m.MSG.Play:
							if(!this.__loaded) break;
							this.hide();
							break;
						case m.MSG.Pause:
							if(!this.__loaded) break;
							this.options.coverpic_pause === !0 && this.setPoster(this.poster.pause);
							break;
						case m.MSG.Ended:
							if(!this.__loaded) break;
							break;
						case m.MSG.Error:
							if(!this.__loaded) break
					}
				}, t.prototype.setPoster = function(e) {
					if(e = e || this.poster.src) {
						this.__preload && (this.__preload.onload = null), this.__preload = new Image;
						var t = this.__preload;
						this.hide();
						var i = this;
						t.onload = function() {
							if(i.pic.src = t.src, i.show(), !f.supportStyle("transform")) {
								var e = "stretch" == i.poster.style;
								if(e) return;
								var o = "cover" == i.poster.style ? i.options.width / (t.width / t.height) : t.height,
									n = "-" + i.options.width / 2 + "px",
									r = "-" + o / 2 + "px";
								i.pic.style.cssText = "left: 50%; top: 50%; margin-left: " + n + "; margin-top: " + r + ";"
							}
						}, t.src = e
					}
				}, t.prototype.toggle = function(e) {
					clearTimeout(this.__tid);
					var t = this;
					this.__tid = setTimeout(function() {
						t.el.style.display = e
					}, 100)
				}, t.prototype.hide = function() {
					this.__preload && (this.__preload.onload = null), this.toggle("none")
				}, t.prototype.show = function() {
					this.toggle("block")
				}, t
			}(u["default"]);
		t["default"] = g
	}, function(e, t, i) {
		"use strict";

		function o(e) {
			if(e && e.__esModule) return e;
			var t = {};
			if(null != e)
				for(var i in e) Object.prototype.hasOwnProperty.call(e, i) && (t[i] = e[i]);
			return t["default"] = e, t
		}

		function n(e) {
			return e && e.__esModule ? e : {
				"default": e
			}
		}

		function r(e, t) {
			if(!(e instanceof t)) throw new TypeError("Cannot call a class as a function")
		}

		function s(e, t) {
			if(!e) throw new ReferenceError("this hasn't been initialised - super() hasn't been called");
			return !t || "object" != typeof t && "function" != typeof t ? e : t
		}

		function l(e, t) {
			if("function" != typeof t && null !== t) throw new TypeError("Super expression must either be null or a function, not " + typeof t);
			e.prototype = Object.create(t && t.prototype, {
				constructor: {
					value: e,
					enumerable: !1,
					writable: !0,
					configurable: !0
				}
			}), t && (Object.setPrototypeOf ? Object.setPrototypeOf(e, t) : e.__proto__ = t)
		}
		t.__esModule = !0;
		var a = i(11),
			c = n(a),
			u = i(2),
			p = (o(u), i(4)),
			h = (o(p), i(3)),
			d = (o(h), {});
		! function(e, t) {
			e.Spinner = t()
		}(d, function() {
			function e(e, t) {
				var i, o = document.createElement(e || "div");
				for(i in t) o[i] = t[i];
				return o
			}

			function t(e) {
				for(var t = 1, i = arguments.length; i > t; t++) e.appendChild(arguments[t]);
				return e
			}

			function i(e, t, i, o) {
				var n = ["opacity", t, ~~(100 * e), i, o].join("-"),
					r = .01 + i / o * 100,
					s = Math.max(1 - (1 - e) / t * (100 - r), e),
					l = c.substring(0, c.indexOf("Animation")).toLowerCase(),
					a = l && "-" + l + "-" || "";
				return h[n] || (u.insertRule("@" + a + "keyframes " + n + "{0%{opacity:" + s + "}" + r + "%{opacity:" + e + "}" + (r + .01) + "%{opacity:1}" + (r + t) % 100 + "%{opacity:" + e + "}100%{opacity:" + s + "}}", u.cssRules.length), h[n] = 1), n
			}

			function o(e, t) {
				var i, o, n = e.style;
				if(t = t.charAt(0).toUpperCase() + t.slice(1), void 0 !== n[t]) return t;
				for(o = 0; o < p.length; o++)
					if(i = p[o] + t, void 0 !== n[i]) return i
			}

			function n(e, t) {
				for(var i in t) e.style[o(e, i) || i] = t[i];
				return e
			}

			function r(e) {
				for(var t = 1; t < arguments.length; t++) {
					var i = arguments[t];
					for(var o in i) void 0 === e[o] && (e[o] = i[o])
				}
				return e
			}

			function s(e, t) {
				return "string" == typeof e ? e : e[t % e.length]
			}

			function l(e) {
				this.opts = r(e || {}, l.defaults, d)
			}

			function a() {
				function i(t, i) {
					return e("<" + t + ' xmlns="urn:schemas-microsoft.com:vml" class="spin-vml">', i)
				}
				u.addRule(".spin-vml", "behavior:url(#default#VML)"), l.prototype.lines = function(e, o) {
					function r() {
						return n(i("group", {
							coordsize: u + " " + u,
							coordorigin: -c + " " + -c
						}), {
							width: u,
							height: u
						})
					}

					function l(e, l, a) {
						t(h, t(n(r(), {
							rotation: 360 / o.lines * e + "deg",
							left: ~~l
						}), t(n(i("roundrect", {
							arcsize: o.corners
						}), {
							width: c,
							height: o.scale * o.width,
							left: o.scale * o.radius,
							top: -o.scale * o.width >> 1,
							filter: a
						}), i("fill", {
							color: s(o.color, e),
							opacity: o.opacity
						}), i("stroke", {
							opacity: 0
						}))))
					}
					var a, c = o.scale * (o.length + o.width),
						u = 2 * o.scale * c,
						p = -(o.width + o.length) * o.scale * 2 + "px",
						h = n(r(), {
							position: "absolute",
							top: p,
							left: p
						});
					if(o.shadow)
						for(a = 1; a <= o.lines; a++) l(a, -2, "progid:DXImageTransform.Microsoft.Blur(pixelradius=2,makeshadow=1,shadowopacity=.3)");
					for(a = 1; a <= o.lines; a++) l(a);
					return t(e, h)
				}, l.prototype.opacity = function(e, t, i, o) {
					var n = e.firstChild;
					o = o.shadow && o.lines || 0, n && t + o < n.childNodes.length && (n = n.childNodes[t + o], n = n && n.firstChild, n = n && n.firstChild, n && (n.opacity = i))
				}
			}
			var c, u, p = ["webkit", "Moz", "ms", "O"],
				h = {},
				d = {
					lines: 12,
					length: 7,
					width: 5,
					radius: 10,
					scale: 1,
					corners: 1,
					color: "#000",
					opacity: .25,
					rotate: 0,
					direction: 1,
					speed: 1,
					trail: 100,
					fps: 20,
					zIndex: 2e9,
					className: "spinner",
					top: "50%",
					left: "50%",
					shadow: !1,
					hwaccel: !1,
					position: "absolute"
				};
			if(l.defaults = {}, r(l.prototype, {
					spin: function(t) {
						this.stop();
						var i = this,
							o = i.opts,
							r = i.el = e(null, {
								className: o.className
							});
						if(n(r, {
								position: o.position,
								width: 0,
								zIndex: o.zIndex,
								left: o.left,
								top: o.top
							}), t && t.insertBefore(r, t.firstChild || null), r.setAttribute("role", "progressbar"), i.lines(r, i.opts), !c) {
							var s, l = 0,
								a = (o.lines - 1) * (1 - o.direction) / 2,
								u = o.fps,
								p = u / o.speed,
								h = (1 - o.opacity) / (p * o.trail / 100),
								d = p / o.lines;
							! function f() {
								l++;
								for(var e = 0; e < o.lines; e++) s = Math.max(1 - (l + (o.lines - e) * d) % p * h, o.opacity), i.opacity(r, e * o.direction + a, s, o);
								i.timeout = i.el && setTimeout(f, ~~(1e3 / u))
							}()
						}
						return i
					},
					stop: function() {
						var e = this.el;
						return e && (clearTimeout(this.timeout), e.parentNode && e.parentNode.removeChild(e), this.el = void 0), this
					},
					lines: function(o, r) {
						function l(t, i) {
							return n(e(), {
								position: "absolute",
								width: r.scale * (r.length + r.width) + "px",
								height: r.scale * r.width + "px",
								background: t,
								boxShadow: i,
								transformOrigin: "left",
								transform: "rotate(" + ~~(360 / r.lines * u + r.rotate) + "deg) translate(" + r.scale * r.radius + "px,0)",
								borderRadius: (r.corners * r.scale * r.width >> 1) + "px"
							})
						}
						for(var a, u = 0, p = (r.lines - 1) * (1 - r.direction) / 2; u < r.lines; u++) a = n(e(), {
							position: "absolute",
							top: 1 + ~(r.scale * r.width / 2) + "px",
							transform: r.hwaccel ? "translate3d(0,0,0)" : "",
							opacity: r.opacity,
							animation: c && i(r.opacity, r.trail, p + u * r.direction, r.lines) + " " + 1 / r.speed + "s linear infinite"
						}), r.shadow && t(a, n(l("#000", "0 0 4px #000"), {
							top: "2px"
						})), t(o, t(a, l(s(r.color, u), "0 0 1px rgba(0,0,0,.1)")));
						return o
					},
					opacity: function(e, t, i) {
						t < e.childNodes.length && (e.childNodes[t].style.opacity = i)
					}
				}), "undefined" != typeof document) {
				u = function() {
					var i = e("style", {
						type: "text/css"
					});
					return t(document.getElementsByTagName("head")[0], i), i.sheet || i.styleSheet
				}();
				var f = n(e("group"), {
					behavior: "url(#default#VML)"
				});
				!o(f, "transform") && f.adj ? a() : c = o(f, "animation")
			}
			return l
		});
		var f = function(e) {
			function t(i) {
				r(this, t);
				var o = s(this, e.call(this, i, "Loading"));
				return o.timeSeed = null, o
			}
			return l(t, e), t.prototype.render = function(t) {
				this.createEl("div", {
					"class": "vcp-loading"
				});
				var i = {
					lines: 11,
					length: 12,
					width: 4,
					radius: 16,
					scale: 1,
					corners: 1,
					color: "#fff",
					opacity: .25,
					rotate: 0,
					direction: 1,
					speed: 1,
					trail: 60,
					fps: 20,
					zIndex: 2e9,
					className: "vcp-spinner",
					top: "50%",
					left: "50%",
					shadow: !1,
					hwaccel: !0,
					position: "absolute"
				};
				new d.Spinner(i).spin(this.el);
				return e.prototype.render.call(this, t)
			}, t.prototype.setup = function() {}, t.prototype.handleMsg = function(e) {}, t.prototype.show = function() {
				if(this.options.showLoading !== !1) {
					var e = 1e3,
						t = this;
					this.timeSeed = setTimeout(function() {
						t.el.style.display = "block"
					}, e)
				}
			}, t.prototype.hide = function() {
				this.timeSeed && (clearTimeout(this.timeSeed), this.timeSeed = null), this.el.style.display = "none"
			}, t
		}(c["default"]);
		t["default"] = f
	}, function(e, t, i) {
		"use strict";

		function o(e) {
			if(e && e.__esModule) return e;
			var t = {};
			if(null != e)
				for(var i in e) Object.prototype.hasOwnProperty.call(e, i) && (t[i] = e[i]);
			return t["default"] = e, t
		}

		function n(e) {
			return e && e.__esModule ? e : {
				"default": e
			}
		}

		function r(e, t) {
			if(!(e instanceof t)) throw new TypeError("Cannot call a class as a function")
		}

		function s(e, t) {
			if(!e) throw new ReferenceError("this hasn't been initialised - super() hasn't been called");
			return !t || "object" != typeof t && "function" != typeof t ? e : t
		}

		function l(e, t) {
			if("function" != typeof t && null !== t) throw new TypeError("Super expression must either be null or a function, not " + typeof t);
			e.prototype = Object.create(t && t.prototype, {
				constructor: {
					value: e,
					enumerable: !1,
					writable: !0,
					configurable: !0
				}
			}), t && (Object.setPrototypeOf ? Object.setPrototypeOf(e, t) : e.__proto__ = t)
		}
		t.__esModule = !0;
		var a = i(11),
			c = n(a),
			u = i(2),
			p = (o(u), i(4)),
			h = (o(p), i(3)),
			d = o(h),
			f = {
                EnvError: "当前系统环境不支持播放该视频格式",
                EnvFlashError: "当前系统环境不支持播放该视频格式",
                VideoSourceError: "获取视频失败，请检查播放链接是否有敿",
                NetworkError: "网络错误，请检查网络配置或者播放链接是否正硿",
                VideoDecodeError: "视频解码错误",
                ArgumentError: "使用参数有误，请检查播放器调用代码",
                UrlEmpty: "请填写视频播放地址",
                FileProtocol: "请勿在file协议下使用播放器，可能会导致视频无法播放",
                LiveFinish: "直播已结板,请稍后再板",
                CrossDomainError: "无法加载视频文件，跨域访问被拒绝",
                Ie9IframeFullscreenError: "在IE9中用iframe引用的实例无法支持全屿"
			},
			y = {
				FileProtocol: [10],
				ArgumentError: [11],
				UrlEmpty: [12],
				LiveFinish: [13],
				VideoSourceError: [1002, 2032],
				EnvError: [4, 5],
				NetworkError: [1001, 1, 2],
				VideoDecodeError: [3],
				CrossDomainError: [2048],
				Ie9IframeFullscreenError: [10001]
			},
			v = function(e) {
				function t(i) {
					r(this, t);
					var o = s(this, e.call(this, i, "ErrorTips"));
					o.customTips = d.extend({}, f, o.options.wording);
					for(var n in y)
						for(var l = 0; l < y[n].length; l++) {
							var a = y[n][l];
							o.customTips[a] = o.customTips[a] || o.customTips[n]
						}
					return o
				}
				return l(t, e), t.prototype.render = function(t) {
					return this.createEl("div", {
						"class": "vcp-error-tips"
					}), e.prototype.render.call(this, t)
				}, t.prototype.setup = function() {}, t.prototype.handleMsg = function(e) {}, t.prototype.show = function(e) {
					this.el.style.display = "block";
					var t = void 0;
					if("string" == typeof e) t = e;
					else {
						var i = this.customTips[e.code] || e.reason;
						t = "[" + e.code + "]" + i
					}
					this.el.innerHTML = d.escapeHTML(t)
				}, t.prototype.hide = function() {
					this.el.style.display = "none"
				}, t.prototype.clear = function() {
					this.el.innerHTML = "", this.hide()
				}, t
			}(c["default"]);
		t["default"] = v
	}])
});