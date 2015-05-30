(ns hello-fig.outtakes
  (:require
    [reagent.core :as reagent :refer [atom]]
    [cljsjs.react :as react]
    [re-frame.core :refer [register-handler path register-sub dispatch subscribe]]
    [re-com.core :refer [h-box v-box box gap line border label title alert-box]
     :refer-macros [handler-fn]]
    [re-com.util :refer [get-element-by-id item-for-id]]))

(defn raphael-component []
  (let [paper (js/Raphael 50 50 100 100)
        circle (. paper (circle 50 50 10))]
    (reagent/create-class
      {:component-did-mount (fn [] (. circle (attr "fill" "#f00")))
       :reagent-render      (fn [])
       :display-name        "raph-component"
       })))

(defn jtab-component [tab]
  (reagent/create-class
    {:component-did-mount (fn [] (.render js/jtab "#mytab" tab))
     :reagent-render      (fn [] [:div#mytab] )
     :display-name        "jtab-component"
     }))

(defn layout []
  (let [mouse-over? (reagent/atom false)]
    (fn []
      [v-box
       :size "auto"
       :children
       [[box
         :child "Header"
         :style {:background-color "silver"}]
        [h-box
         :size "auto"
         :children [[box
                     :size "200px"
                     :child "Nav"
                     :width "200px"
                     :height "200px"
                     :style (if @mouse-over? {:background-color "#e8e8e8"}
                                             {:background-color "plum"})
                     :attr {:on-mouse-over (handler-fn (reset! mouse-over? true))
                            :on-mouse-out (handler-fn (reset! mouse-over? false))}
                     ]
                    [
                     box
                     :size "auto"
                     ;; :child [jtab-component "E / / / | Am / Bb / ||"]
                     :child [jtab-component "$3.6.$2.5h7 $1 5 $2 7"]
                     ;;:style {:background-color "aliceblue"}
                     ]]]
        [box
         :child "Footer"
         :style {:background-color "silver"}
         ]
        ]]
      )))