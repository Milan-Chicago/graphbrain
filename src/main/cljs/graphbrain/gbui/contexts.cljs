(ns graphbrain.gbui.contexts
  (:require-macros [hiccups.core :as hiccups])
  (:require [graphbrain.gbui.globals :as g]
            [graphbrain.gbui.user :as user]
            [jayq.core :as jq]
            [hiccups.runtime :as hiccupsrt])
  (:use [jayq.core :only [$]]))

(def initialised (atom false))

(defn targ-ctxt
  ([]
     @g/context)
  ([ctxts]
     (if (some #{@g/context} ctxts)
       @g/context
       (user/id))))

(hiccups/defhtml create-context-dialog-template
  []
  [:div {:class "modal" :role "dialog" :aria-hidden "true" :id "create-context-modal"}
   [:div {:class "modal-dialog"}
    [:div {:class "modal-content"}
     [:div {:class "modal-header"}
      [:a {:class "close" :data-dismiss "modal"} "×"]
      [:h3 "Create Context"]]
     [:form {:id "create-context-form"
             :action "/create-context"
             :method "post"}
      [:div {:class "modal-body" :id "create-context-body"}
       [:span {:id "create-context-error-message" :class "text-danger"}]
       [:div {:id "name-formgroup" :class "form-group"}
        [:label {:class "control-label"} "Name"]
        [:input {:id "su-name"
                 :name "name"
                 :type "text"
                 :class "form-control input-sm"
                 :placeholder "Short name"}]]
       [:div {:id "desc-formgroup" :class "form-group"}
        [:label {:class "control-label"} "Description"]
        [:input {:id "su-desc"
                 :name "desc"
                 :type "text"
                 :class "form-control input-sm"
                 :placeholder "What is is about?"}]]]
      [:div {:class "modal-footer"}
       [:button
        {:type "submit" :class "btn btn-primary"} "Create"]]]]]])

(defn clear-create-context-errors!
  []
  (.removeClass ($ "#name-formgroup") "has-error")
  (.removeClass ($ "#desc-formgroup") "has-error")
  (.html ($ "#create-content-error-message") ""))

(defn init-dialogs!
  []
  (.appendTo
   ($ (create-context-dialog-template)) "body"))

(defn show-create-context-dialog!
  []
  (if (not @initialised)
    (do
      (init-dialogs!)
      (reset! initialised true)))
  (.modal ($ "#create-context-modal") "show"))